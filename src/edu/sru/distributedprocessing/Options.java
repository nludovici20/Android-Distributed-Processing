package edu.sru.distributedprocessing;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;

public class Options extends ExpandableListActivity
{
    static final String navigation_options[] = {
	  "Vehicles", "Drivers", "Shipments","Routing","Contractors","Depots",
	  "Warehouses", "Vehicle Type", "Maintenance", "Technicians", "Contacts",
	  "Reports"
	};

	static final String field_options[][] = {
		
	  { //Vehicle fields
		"ID","Plate Number","Vin Number",
		"Manufactured Year", "Vehicle Type",
		"Driver","Depot", "Available?"
	  },
	  
	  { //Driver Fields
		"ID", "Last Name", "First Name", 
		"Vehicle Plate No.", "License Number", 
		"License Expiration", "License Class" 
	  },
	  
	  { //Shipments Fields 
		 "" 
	  },
	  
	  { //Routing Fields   
		  ""
	  },
	  
	  { //Contractors Fields 
		  ""
	  },
	  
	  { //Depots Fields 
		  "ID", "Name", "Address", "City", "State",
		  "Zip Code", "Latitude", "Longitude"
	  },
	  
	  { //Warehouses Fields 
		  ""
	  },
	  
	  { //Vehicle Types Fields 
		  "ID", "SubType", "Model", "Max Weight",
		  "Max Range", "Length" 
	  },
	  
	  { //Maintenance Fields 
		  ""
	  },
	  
	  { //Technicians Fields 
		  ""
	  },
	  
	  { //Contacts Fields 
			"ID", "Last Name", "First Name", 
			"Middle Initial", "Primary Phone",
			"Work Phone"
	  },
	  
	  { //Reports Fields 
		  ""
	  },
	  
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_main);
		SimpleExpandableListAdapter expListAdapter =
			new SimpleExpandableListAdapter(
				this,
				createGroupList(),	// groupData describes the first-level entries
				R.layout.group_row,	// Layout for the first-level entries
				new String[] { "Navigation_Options" },	// Key in the groupData maps to display
				new int[] { R.id.childname },		// Data under "Navigation_Options" key goes into this TextView
				createChildList(),	// childData describes second-level entries
				R.layout.fields_row,	// Layout for second-level entries
				new String[] { "Field_Options" },	// Keys in childData maps to display
				new int[] { R.id.childname}	// Data under the keys above go into these TextViews
			);
		setListAdapter( expListAdapter );
    }

    public void  onContentChanged  () {
        super.onContentChanged();
    }

    public boolean onChildClick(
            ExpandableListView parent, 
            View v, 
            int groupPosition,
            int fieldPosition,
            long id) {
        CheckBox cb = (CheckBox)v.findViewById( R.id.check_box );
        if( cb != null )
            cb.toggle();
        return false;
    }

    public void  onGroupExpand  (int groupPosition) {
    	//log change
    }

/**
  * Creates the group list out of the colors[] array according to
  * the structure required by SimpleExpandableListAdapter. The resulting
  * List contains Maps. Each Map contains one entry with key "Navigation_Options" and
  * value of an entry in the colors[] array.
  */
	private List<HashMap<String, String>> createGroupList() {
	  ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
	  for( int i = 0 ; i < navigation_options.length ; ++i ) {
		HashMap<String, String> groups = new HashMap<String, String>();
	    groups.put( "Navigation_Options",navigation_options[i] );
		result.add( groups );
	  }
	  return (List<HashMap<String, String>>)result;
    }

/**
  * Creates the child list out of the shades[] array according to the
  * structure required by SimpleExpandableListAdapter. The resulting List
  * contains one list for each group. Each such second-level group contains
  * Maps. Each such Map contains two keys: "Field_Options" is the name of the
  * shade and "rgb" is the RGB value for the shade.
  */
  private List<ArrayList<HashMap<String, String>>> createChildList() {
	ArrayList<ArrayList<HashMap<String, String>>> result = new ArrayList<ArrayList<HashMap<String, String>>>();
	for( int i = 0 ; i < field_options.length ; ++i ) {
		// Second-level lists
	  ArrayList<HashMap<String, String>> field_options_list = new ArrayList<HashMap<String, String>>();
	  for( int n = 0 ; n < field_options[i].length ; n++ ) {
	    HashMap<String, String> child = new HashMap<String, String>();
		child.put( "Field_Options", field_options[i][n] );
		field_options_list.add( child );
	  }
	  result.add( field_options_list );
	}
	return result;
  }

}
