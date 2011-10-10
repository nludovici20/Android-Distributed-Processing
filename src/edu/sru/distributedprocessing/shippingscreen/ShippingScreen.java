package edu.sru.distributedprocessing.shippingscreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.CustomDialogListView;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tools.Constants;

public class ShippingScreen 
{
	private ArrayList<Record> records;
	private Record[] type;
	private Activity act;
	private int vehicleIndex, tableSizeLimit;
	
	//Table constructor
	public ShippingScreen(Activity act, ArrayList<Record> rec)
	{
		this.act = act;
		this.records = rec;
		this.type = rec.toArray(new Record[1]);	
	}
		
	public void Initialize() 
	{
		//Collection<String> fields = null;
		String[] fields = new String[2];
		//set the vehicle objects in view to the screen
		Log.v("Distributed-Processing", type[0].getRecordType() + "  " + Constants.db.getTables()[0].getRecords()[0].getRecordType());
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			try
			{
				if(type[0].getRecordType().equalsIgnoreCase(Constants.db.getTables()[i].getRecords()[0].getRecordType()))
				{
					fields = Constants.db.getTables()[i].getFieldsInView().toArray(new String[1]);
					vehicleIndex = Constants.db.getTables()[i].getIndex();
					tableSizeLimit = Constants.db.getTables()[i].tableSize();
				}
			}catch (Exception e)
			{
				//handle
			}
		}
		
		TextView field1 = (TextView) act.findViewById(R.id.header_txt1);
		TextView field2 = (TextView) act.findViewById(R.id.header_txt2);
		try
		{
			field1.setText(fields[0].toString());
			field2.setText(fields[1].toString());
		}
		 catch(Exception e)
		 {
			field1.setText(type[0].getField(type[0].getFields()[0].toString()).getFieldName());
			field2.setText(type[0].getField(type[0].getFields()[1].toString()).getFieldName());
			Log.v("Distributed-Processing", "No Fields Selected");
		 }
		
		 //scroll through Vehicles[] and add to listview
		 ListView lv = (ListView) act.findViewById(R.id.listView1);
		 ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();	
		 HashMap<String, String> map;		
		 
		 if(vehicleIndex + 100 > tableSizeLimit)
		 {
			 vehicleIndex = tableSizeLimit - 100;
		 }else
			 if(vehicleIndex < 0)
			 {
				 vehicleIndex = 0;
			 }
		 for(int i = vehicleIndex; i < (vehicleIndex + 100); i++)
		 {
			 if(!(type[i] == null))
			 {
					 map = new HashMap<String, String>();
					 map.put(field1.getText().toString(), type[i].getField(field1.getText().toString()).getValue());
					 map.put(field2.getText().toString(), type[i].getField(field2.getText().toString()).getValue());
					 mylist.add(map);
			 }
		 }
		
		 SimpleAdapter mRecords = new SimpleAdapter(act, mylist, R.layout.list_item, 
    		  new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		 lv.setAdapter(mRecords);
		 lv.setTextFilterEnabled(true);
		 lv.setOnItemClickListener(new OnItemClickListener() 
		 {
    		public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) 
    		{
    			CustomDialogListView cldv = new CustomDialogListView(act, R.style.CustomDialogTheme, type[arg2]);
    			cldv.show();
    		}
       });
	}

	public void Update() 
	{
		//update the data and show on screen
	}

	public void Finalize() 
	{
		//save everything and exit
	}

	public Record[] getType()
	{
		return this.type;
	}
}
