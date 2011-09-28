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
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.CustomDialogListView;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tools.Constants;

public class ShippingScreen 
{
	Record[] type;
	Activity act;
	
	//Table constructor
	public ShippingScreen(Activity act, Record[] type)
	{
		this.act = act;
		this.type = type;	
	}
		
	public void Initialize() 
	{
		Collection<String> fields = null;
		//set the vehicle objects in view to the screen
		if(this.type[0].getRecordType().equalsIgnoreCase("Vehicle"))
		{
			fields = Constants.vehicle_fields.values();
		}else
			if(this.type[0].getRecordType().equalsIgnoreCase("Driver"))
			{
				fields = Constants.driver_fields.values();
			}else
				if(this.type[0].getRecordType().equalsIgnoreCase("Depot"))
				{
					fields = Constants.depot_fields.values();
				}else
					if(this.type[0].getRecordType().equalsIgnoreCase("Contact"))
					{
						fields = Constants.contact_fields.values();
					}else
						if(this.type[0].getRecordType().equalsIgnoreCase("Vehicle Type"))
						{
							fields = Constants.vehicle_type_fields.values();
						}
		
		TextView field1 = (TextView) act.findViewById(R.id.header_txt1);
		TextView field2 = (TextView) act.findViewById(R.id.header_txt2);
		try
		{
			field1.setText(fields.toArray()[0].toString());
			field2.setText(fields.toArray()[1].toString());
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
		 for(int i = 0; i < type.length; i++)
		 {
			 if(!(type[i] == null))
			 {
				 map = new HashMap<String, String>();
				 map.put(field1.getText().toString(), type[i].getField(field1.getText().toString()).getValue());
				 map.put(field2.getText().toString(), type[i].getField(field2.getText().toString()).getValue());
				 mylist.add(map);
			 }
		 }
		 SimpleAdapter mSchedule = new SimpleAdapter(act, mylist, R.layout.list_item, 
    		  new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		 lv.setAdapter(mSchedule);
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
