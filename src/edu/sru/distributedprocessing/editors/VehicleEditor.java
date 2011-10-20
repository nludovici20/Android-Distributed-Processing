package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class VehicleEditor extends Activity {
	String tableName;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "vehicles";
        
        //Editor Items
        TextView group_header = (TextView) findViewById(R.id.group_header);
        EditText vin_text = (EditText) findViewById(R.id.vin_edit);
        EditText license_text = (EditText) findViewById(R.id.license_edit);
        EditText year_text = (EditText) findViewById(R.id.year_edit); 
        CheckBox avail_box = (CheckBox) findViewById(R.id.available_box);
        Spinner driver_group = (Spinner) findViewById(R.id.driver_choices);
        Spinner vehicle_type_group = (Spinner) findViewById(R.id.vehicle_choices);
        Spinner depot_group = (Spinner) findViewById(R.id.depot_choices);
        
        /*
         *  Pass in record ID selected
         *  Ask database for entire record selected
         *  Store in temp Record[]
         *  Read from record and insert into corresponding editor items
         * 
         */
        
        /*
         * For Driver, Vehicle Type, and Depot Pull in entire column from database
         * store in a local array(list)?
         *
         */
     
       
        }
        
       
}