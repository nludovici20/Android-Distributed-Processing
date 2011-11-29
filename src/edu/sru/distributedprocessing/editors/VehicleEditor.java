package edu.sru.distributedprocessing.editors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.InsertLoading;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tools.Constants;

public class VehicleEditor extends Activity {
	private String tableName, intent;
	int index;
	private TextView group_header;
	private EditText vin_text, license_text, year_text, driver_group, vehicle_type_group, depot_group;
	private CheckBox avail_box;
	private String[] fields, new_record;
	private Intent engineIntent;
	private Button save_btn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "vehicles";
        
        intent = getIntent().getExtras().getString("Intent");
        
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        /***** Editor Items *****/
        
        if(intent.equalsIgnoreCase("edit"))
        {
	        group_header = (TextView) findViewById(R.id.group_header);
	        group_header.setText(group_header.getText().toString() + " " + Constants.record.get(fields[0]));
			        
	        license_text = (EditText) findViewById(R.id.licenseNO_edit);
	        license_text.setText(Constants.record.get(fields[1]));
	
	        vin_text = (EditText) findViewById(R.id.vinNO_edit);
	        vin_text.setText(Constants.record.get(fields[2]));
	        
	        year_text = (EditText) findViewById(R.id.year_edit); 
	        year_text.setText(Constants.record.get(fields[3]));
	
	        vehicle_type_group = (EditText) findViewById(R.id.vehicleType_edit);
	        vehicle_type_group.setText(Constants.record.get(fields[4]));
	        
	        driver_group = (EditText) findViewById(R.id.driverChoices_edit);
	        driver_group.setText(Constants.record.get(fields[5]));
	        
	        depot_group = (EditText) findViewById(R.id.depotChoices_edit);   
	        depot_group.setText(Constants.record.get(fields[6]));
	        
	        avail_box = (CheckBox) findViewById(R.id.available_box);
	        
	        Log.v("ADP", "ERROR AT " + Constants.record.get(fields[6]));
	        
	        if(Integer.parseInt(Constants.record.get(fields[7])) == 1)
	        {
	        	avail_box.setChecked(true);
	        }else
	        {
	        	avail_box.setChecked(false);
	        }
        
        }
        save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Toast.makeText(VehicleEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				if(intent.equalsIgnoreCase("edit"))
				{
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = license_text.getText().toString();
					new_record[2] = vin_text.getText().toString();
					new_record[3] = year_text.getText().toString();
					new_record[4] = vehicle_type_group.getText().toString();
					new_record[5] = driver_group.getText().toString();
					new_record[6] = depot_group.getText().toString();
					if(avail_box.isChecked())
					{
						new_record[7] = ""+1;
					}else
					{
						new_record[7] = ""+0;
					} 
					
					Log.v("ADP", "ContactEditor - Edit Request");
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						new_record[0] = license_text.getText().toString();
						new_record[1] = vin_text.getText().toString();
						new_record[2] = year_text.getText().toString();
						new_record[3] = vehicle_type_group.getText().toString();
						new_record[4] = driver_group.getText().toString();
						new_record[5] = depot_group.getText().toString();
						if(avail_box.isChecked())
						{
							new_record[6] = ""+1;
						}else
						{
							new_record[6] = ""+0;
						} 
						Log.v("ADP", "DriverEditor.class - Insert Request");
					}
				
				engineIntent = new Intent(VehicleEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				startActivity(engineIntent);
				VehicleEditor.this.finish();
			}
			
		});     
       
    }
}