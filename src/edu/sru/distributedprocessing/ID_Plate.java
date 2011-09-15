package edu.sru.distributedprocessing;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ID_Plate extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.id_plate);
			
		  ListView lv = (ListView) findViewById(R.id.listView1);
		  TextView tv1 = (TextView) findViewById(R.id.header_txt1);
		  TextView tv2 = (TextView) findViewById(R.id.header_txt2);
		  tv1.setText("ID");
		  tv2.setText("LICENSE PLATE");
		  
		  ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("ID", "1");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "2");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "3");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "4");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "5");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "6");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "7");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "8");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "9");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "10");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "11");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "12");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "13");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "14");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  map.put("ID", "15");
		  map.put("LICENSE_PLATE", "DEC-DFE1");
		  mylist.add(map);
		  map = new HashMap<String, String>();
		  map.put("ID", "16");
		  map.put("LICENSE_PLATE", "KD8-2GX");
		  mylist.add(map);
		  // ...
		  SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.list_item,
		              new String[] {"ID", "LICENSE_PLATE"}, new int[] {R.id.ID_CELL, R.id.LICENSE_PLATE_CELL});
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
