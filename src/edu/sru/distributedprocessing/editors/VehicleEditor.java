package edu.sru.distributedprocessing.editors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;

public class VehicleEditor extends Activity {
	private String tableName, intent;
	int index;
	private TextView group_header;
	private EditText vin_text, license_text, year_text;
	private CheckBox avail_box;
	private Spinner driver_group, vehicle_type_group, depot_group;
	private String[] fields, new_record;
	private Intent engineIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "vehicles";
        intent = getIntent().getExtras().getString("Intent");
        index = getIntent().getExtras().getInt("Index");
        
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        //Editor Items
        group_header = (TextView) findViewById(R.id.group_header);
        vin_text = (EditText) findViewById(R.id.vinNO_edit);
        license_text = (EditText) findViewById(R.id.licenseNO_edit);
        year_text = (EditText) findViewById(R.id.year_edit); 
        avail_box = (CheckBox) findViewById(R.id.available_box);
        driver_group = (Spinner) findViewById(R.id.driverChoices_edit);
        vehicle_type_group = (Spinner) findViewById(R.id.vehicleType_edit);
        depot_group = (Spinner) findViewById(R.id.depotChoices_edit);
        
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