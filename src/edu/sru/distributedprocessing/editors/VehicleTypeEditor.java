package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.InsertLoading;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleTypeEditor extends Activity {
	String tableName, intent;
	int index;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_type_editor);
       
        tableName = "vehicle type";
        intent = getIntent().getExtras().getString("Intent");
        index = getIntent().getExtras().getInt("Index");
        
        final String[] fields = getIntent().getExtras().getStringArray("Fields");
        final String[] new_record = new String[fields.length];
        
        //Editor Items
        TextView header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        final EditText vehicleType_edit = (EditText)findViewById(R.id.type_edit);
        vehicleType_edit.setText(Constants.record.get(fields[1]));
        
        final EditText subType_edit = (EditText)findViewById(R.id.subType_edit);
        subType_edit.setText(Constants.record.get(fields[2]));
        
        final EditText vehicleModel_edit = (EditText)findViewById(R.id.model_edit);
        vehicleModel_edit.setText(Constants.record.get(fields[3]));
        
        final EditText maxWeight_edit = (EditText)findViewById(R.id.weight_edit);
        maxWeight_edit.setText(Constants.record.get(fields[4]));
        final EditText maxRange_edit = (EditText)findViewById(R.id.range_edit);
        maxRange_edit.setText(Constants.record.get(fields[5]));
        
        final EditText maxLength_edit = (EditText)findViewById(R.id.length_edit);
        maxLength_edit.setText(Constants.record.get(fields[6]));
       
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(VehicleTypeEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				new_record[0] = Constants.record.get(fields[0]);
				new_record[1] = vehicleType_edit.getText().toString();
				new_record[2] = subType_edit.getText().toString();
				new_record[3] = vehicleModel_edit.getText().toString();
				new_record[4] = maxWeight_edit.getText().toString();
				new_record[5] = maxRange_edit.getText().toString();
				new_record[6] = maxLength_edit.getText().toString();
				if(intent.equalsIgnoreCase("edit"))
				{
					Log.v("ADP", "ContactEditor - Edit Request");
					String[] tmp = new String[2];
					int count = 0;
					for(int i = 0; i < Constants.db.getTable(tableName).getFields().length; i++)
					{
						if(Constants.db.getTable(tableName).getFieldsInView().get(0).equalsIgnoreCase(Constants.db.getTable(tableName).getFields()[i]) || Constants.db.getTable(tableName).getFieldsInView().get(1).equalsIgnoreCase(Constants.db.getTable(tableName).getFields()[i]))
						{
							tmp[count] = new_record[i];
							count++;
							Log.v("ADP", new_record[i]);
						}
					}
					IntelliSyncActivity.ss.changeRecordAt(index, tmp);
				}
				
				VehicleTypeEditor.this.finish();
				Intent engineIntent = new Intent(VehicleTypeEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				startActivity(engineIntent);
			}
			
		});
     
       
        }
        
       
}