package edu.sru.distributedprocessing.shippingscreen;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.CustomDialogListView;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;

/**
 * This class is used by the main IntelliSyncActivity class to handle the instantiation, and manipulation of data the user is viewing.
 * 
 * @author Nick Ludovici
 */
public class ShippingScreen 
{
	private Record[] type; /** records currently in the list view **/
	private Activity act;
	private int tableIndex, tableSizeLimit;
	private Table table; /** table the records currently in view belong to**/
	private SimpleAdapter mRecords;
	private  ListView lv;
	private TextView field1, field2;
	private ArrayList<HashMap<String, String>> mylist;
	
	/**
	 * Constructor that populates the list view with data for the user to view.
	 * 
	 * @param act the IntelliSyncActivity that is calling the class.
	 * @param table the table that is currently being viewed .
	 */
	public ShippingScreen(Activity act, Table table)
	{
		this.act = act;
		this.table = table;
		this.type = table.getRecords();	
	}
		
	/**
	 * Method that populates the list view with data sent by the server.
	 * This method also handles when a record is selected from the list view and handles the pop up options.
	 */
	public void Initialize() 
	{
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
		
		//field headers
		this.field1 = (TextView) act.findViewById(R.id.header_txt1);
		this.field2 = (TextView) act.findViewById(R.id.header_txt2);
		try
		{
			//set the field headers from file
			this.field1.setText(fields[0].toString());
			this.field2.setText(fields[1].toString());
		}
		 catch(Exception e)
		 {
			 //else set general field headers
			this.field1.setText(table.getFields()[0]);
			this.field2.setText(table.getFields()[1]);
			Log.v("ADP", "No Fields Selected");
		 }
		
		 //scroll through Vehicles[] and add to listview
		 lv = (ListView) act.findViewById(R.id.listView1);
		 mylist = new ArrayList<HashMap<String, String>>();	
		 HashMap<String, String> map;		
		 
		 for(int i = 0; i < tableSizeLimit; i++)
		 {
			 try{
				 if(!(type[i] == null))
				 {
					 //create the items to be put in the list
					 map = new HashMap<String, String>();
					 map.put(this.field1.getText().toString(), type[i].getFields()[0]); //header name, value
					 map.put(this.field2.getText().toString(), type[i].getFields()[1]); //header name, value
					 mylist.add(map);
				 }
			 }
			 catch(Exception e)
			 {
				 //handle exception
			 } 
		 }
		
		//ListView Adapter -> Holds List Items (records)
		mRecords = new SimpleAdapter(act, mylist, R.layout.list_item,
				new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		lv.setAdapter(mRecords);
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) 
			{
				//click, pop up dialog box with options
    			CustomDialogListView cldv = new CustomDialogListView(act, R.style.CustomDialogTheme, table, Integer.parseInt(table.getRecords()[arg2].getID()), arg2);
    			Log.v("ADP", "ShippingScreen.class - tablename: " + table.getTableName() + " Record ID: " +  table.getRecords()[arg2].getID());
    			cldv.show();
    		}
       });
		
	}
	
	/**
	 * Method that re-initializes the list view when any type of change has occured to the background data.
	 */
	public void refresh()
	{
		Initialize();
	}

	/**
	 * Method that returns the records that are shown in the list view.
	 * 
	 * @return the records shown in the list view.
	 */
	public Record[] getRecords()
	{
		return this.type;
	}

	/**
	 * Method that removes a record with ID passed in from the Database.
	 * 
	 * @param index the ID value of the record to be deleted.
	 */
	public void deleteRecordAt(String index) {
		Log.v("ADP", "Deleting Record with ID: " + index);
		table.deleteRecord(index);
		type = table.getRecords();
		Initialize();
	}
	
	/**
	 * Method that changes a record at the specified ID with the values passed in.
	 * 
	 * @param index the ID value of the record to be modified.
	 * @param values the values of the record that needs to be updated.
	 */
	public void changeRecordAt(int index,String[] values)
	{
		Log.v("ADP", "Updating Record");
		table.changeRecord(index, values);
		type = table.getRecords();
		Initialize();
	}
	
	/**
	 * Method that inserts a new record into the table.
	 * 
	 * @param index the ID value of the record to be inserted.
	 * @param rec the record to be inserted to the table.
	 */
	public void insertRecordAt(String index, String[] rec)
	{
		Log.v("ADP", "Inserting new Record");
		table.addRecord(new Record(index, rec));
		type = table.getRecords();
		Initialize();
	}
	
}
