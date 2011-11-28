package edu.sru.distributedprocessing.editors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.InsertLoading;
import edu.sru.distributedprocessing.tools.Constants;

public class VehicleTypeEditor extends Activity {
	private String tableName, intent;
	int index;
	private String[] fields, new_record;
	private TextView header;
	private EditText vehicleType_edit, subType_edit, vehicleModel_edit, maxWeight_edit, maxRange_edit, maxLength_edit;
	private Button save_btn;
	private Intent engineIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_type_editor);
       
        tableName = "vehicle type";
        intent = getIntent().getExtras().getString("Intent");
        index = getIntent().getExtras().getInt("Index");
        
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        //Editor Items
        header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        vehicleType_edit = (EditText)findViewById(R.id.type_edit);
        vehicleType_edit.setText(Constants.record.get(fields[1]));
        
        subType_edit = (EditText)findViewById(R.id.subType_edit);
        subType_edit.setText(Constants.record.get(fields[2]));
        
        vehicleModel_edit = (EditText)findViewById(R.id.model_edit);
        vehicleModel_edit.setText(Constants.record.get(fields[3]));
        
        maxWeight_edit = (EditText)findViewById(R.id.weight_edit);
        maxWeight_edit.setText(Constants.record.get(fields[4]));
        
        maxRange_edit = (EditText)findViewById(R.id.range_edit);
        maxRange_edit.setText(Constants.record.get(fields[5]));
        
       maxLength_edit = (EditText)findViewById(R.id.length_edit);
        maxLength_edit.setText(Constants.record.get(fields[6]));
       
        save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(VehicleTypeEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				
				if(intent.equalsIgnoreCase("edit"))
				{
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = vehicleType_edit.getText().toString();
					new_record[2] = subType_edit.getText().toString();
					new_record[3] = vehicleModel_edit.getText().toString();
					new_record[4] = maxWeight_edit.getText().toString();
					new_record[5] = maxRange_edit.getText().toString();
					new_record[6] = maxLength_edit.getText().toString();
					Log.v("ADP", "ContactEditor - Edit Request");
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						new_record[0] = vehicleType_edit.getText().toString();
						new_record[1] = subType_edit.getText().toString();
						new_record[2] = vehicleModel_edit.getText().toString();
						new_record[3] = maxWeight_edit.getText().toString();
						new_record[4] = maxRange_edit.getText().toString();
						new_record[5] = maxLength_edit.getText().toString();
						Log.v("ADP", "VehicleTypeEditor.class - Insert Request");
					}
				
				VehicleTypeEditor.this.finish();
				engineIntent = new Intent(VehicleTypeEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				startActivity(engineIntent);
			}
			
		});
     
       
        }
        
       
}