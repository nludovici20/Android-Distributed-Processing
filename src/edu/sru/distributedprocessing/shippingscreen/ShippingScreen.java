package edu.sru.distributedprocessing.shippingscreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.CustomDialogListView;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;

public class ShippingScreen 
{
	private ArrayList<Record> records;
	private Record[] type;
	private Activity act;
	private int tableIndex, tableSizeLimit;
	private Table table;
	private SimpleAdapter mRecords;
	private  ListView lv;
	private TextView field1, field2;
	private ArrayList<HashMap<String, String>> mylist;
	
	//Table constructor
	public ShippingScreen(Activity act, Table table)
	{
		this.act = act;
		this.table = table;
		this.type = table.getRecords();	
	}
		
	public void Initialize() 
	{
		populateListView();
		mRecords = new SimpleAdapter(act, mylist, R.layout.list_item,
				new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		 lv.setAdapter(mRecords);
		 lv.setTextFilterEnabled(true);
		 lv.setOnItemClickListener(new OnItemClickListener() 
		 {
    		public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) 
    		{
    			CustomDialogListView cldv = new CustomDialogListView(act, R.style.CustomDialogTheme, table, arg2 + 1);
    			Log.v("ADP", "ShippingScreen.class - tablename: " + table.getTableName() + " Record ID: " + arg2 + 1);
    			cldv.show();
    		}
       });
	}

	private void populateListView() {
		//Collection<String> fields = null;
		String[] fields = new String[2];
		//set the vehicle objects in view to the screen
		Log.v("Distributed-Processing", table.getRecordType() + "  " + Constants.db.getTables()[0].getRecordType());
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			try
			{
				if(table.getRecordType().equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
				{
					fields = Constants.db.getTables()[i].getFieldsInView().toArray(new String[1]);
					tableIndex = Constants.db.getTables()[i].getIndex();
					tableSizeLimit = Constants.db.getTables()[i].tableSize();
				}
			}catch (Exception e)
			{
				//handle
			}
		}
		
		this.field1 = (TextView) act.findViewById(R.id.header_txt1);
		this.field2 = (TextView) act.findViewById(R.id.header_txt2);
		try
		{
			field1.setText(fields[0].toString());
			field2.setText(fields[1].toString());
		}
		 catch(Exception e)
		 {
			field1.setText(table.getFields()[0]);
			field2.setText(table.getFields()[1]);
			Log.v("ADP", "No Fields Selected");
		 }
		
		 //scroll through Vehicles[] and add to listview
		 lv = (ListView) act.findViewById(R.id.listView1);
		 mylist = new ArrayList<HashMap<String, String>>();	
		 HashMap<String, String> map;		
		 
		 if(tableIndex + 100 > tableSizeLimit)
		 {
			 tableIndex = tableSizeLimit - 100;
		 }else
			 if(tableIndex < 0)
			 {
				 tableIndex = 0;
			 }
		 for(int i = tableIndex; i < (tableIndex + 100); i++)
		 {
			 try{
				 if(!(type[i] == null))
				 {
						 map = new HashMap<String, String>();
						 map.put(field1.getText().toString(), type[i].getFields()[0]);
						 map.put(field2.getText().toString(), type[i].getFields()[1]);
						 mylist.add(map);
				 }
			 }
			 catch(Exception e)
			 {
				 
			 }
		 }
	}

	public void Update() 
	{
		Log.d("ADP", "Inside Update");
		populateListView();
		mRecords = new SimpleAdapter(act, mylist, R.layout.list_item,
				new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		lv.setAdapter(mRecords);
		Log.d("TCP", "Refreshed List");
		Log.d("ADP", "Exit Update");
	}

	public void Finalize() 
	{
		//save everything and exit
	}

	public Record[] getType()
	{
		return this.type;
	}

	public void deleteRecordAt(int index) {
		mylist.remove(index);
		
	}
	
}
