package edu.sru.distributedprocessing.screentypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import edu.sru.distributedprocessing.Constants;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.tools.Vehicles;

public class VehicleType extends ScreenType 
{
	Vehicles[] vehicles;
	Activity act;
	public VehicleType(Activity act, Vehicles[] vehicles)
	{
		this.act = act;
		this.vehicles = vehicles;		
	}
	@Override
	public void Initialize() 
	{
		//set the vehicle objects in view to the screen
		Collection<String> fields = Constants.vehicle_fields.values();
		TextView field1 = (TextView) act.findViewById(R.id.header_txt1);
		TextView field2 = (TextView) act.findViewById(R.id.header_txt2);
		try
		{
			field1.setText(fields.toArray()[0].toString());
			field2.setText(fields.toArray()[1].toString());
		}
		 catch(Exception e)
		 {
			field1.setText("ID");
			field2.setText("License Plate Number");
		 }
		
		 //scroll through Vehicles[] and add to listview
		 ListView lv = (ListView) act.findViewById(R.id.listView1);
		 ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();	
		 HashMap<String, String> map;		
		 for(int i = 0; i < vehicles.length; i++)
		 {
			 map = new HashMap<String, String>();
			 map.put(field1.getText().toString(), vehicles[i].getField(field1.getText().toString()).getValue());
			 map.put(field2.getText().toString(), vehicles[i].getField(field2.getText().toString()).getValue());
			 mylist.add(map);
		 }
		 SimpleAdapter mSchedule = new SimpleAdapter(act, mylist, R.layout.list_item, 
    		  new String[] {field1.getText().toString(), field2.getText().toString()}, new int[] {R.id.FIELD1, R.id.FIELD2});
		 lv.setAdapter(mSchedule);
		 lv.setTextFilterEnabled(true);
		 lv.setOnItemClickListener(new OnItemClickListener() 
		 {
    		public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) 
    		{
    			
    		}
       });
	}

	@Override
	public void Update() 
	{
		//update the data and show on screen
	}

	@Override
	public void Finalize() 
	{
		//save everything and exit
	}

	public Vehicles[] getVehicles()
	{
		return this.vehicles;
	}
}