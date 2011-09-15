package edu.sru.distributedprocessing;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Type_Driver extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.id_plate);
		
		  ListView lv = (ListView) findViewById(R.id.listView1);
		  TextView tv1 = (TextView) findViewById(R.id.header_txt1);
		  TextView tv2 = (TextView) findViewById(R.id.header_txt2);
		  tv1.setText("VEHICLE TYPE");
		  tv2.setText("DRIVER");
		  
		  ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("TYPE", "Flatbed");
		  map.put("DRIVER", "McDonald, Mary");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("TYPE", "Flatbed");
		  map.put("DRIVER", "Niece, Sue");
		  mylist.add(map);
		  // ...
		  SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.list_item,
		              new String[] {"TYPE", "DRIVER"}, new int[] {R.id.ID_CELL, R.id.LICENSE_PLATE_CELL});
		  lv.setAdapter(mSchedule);
		  lv.setTextFilterEnabled(true);
		  lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
			}
		  });
	}
}
