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

public class DriverEditor extends Activity {
	private String tableName, intent;
	//private int index;
	private String[] fields, new_record;
	private TextView header;
	private EditText firstName_edit, lastName_edit, plateNO_edit, licenseNO_edit, licenseEXP_edit, licenseClass_edit;
	private Button save_btn;
	private Intent engineIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "drivers";
        intent = getIntent().getExtras().getString("Intent");
        //index = getIntent().getExtras().getInt("Index");
        
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        //Editor Items
        header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        firstName_edit = (EditText)findViewById(R.id.firstName_edit);
        firstName_edit.setText(Constants.record.get(fields[1]));
        
        lastName_edit = (EditText)findViewById(R.id.lastName_edit);
        lastName_edit.setText(Constants.record.get(fields[2]));
        
        plateNO_edit = (EditText)findViewById(R.id.plateNO_edit);
        plateNO_edit.setText(Constants.record.get(fields[3]));
        
        licenseNO_edit = (EditText)findViewById(R.id.licenseNO_edit);
        licenseNO_edit.setText(Constants.record.get(fields[4]));
        
        licenseEXP_edit = (EditText)findViewById(R.id.licenseEXP_edit);
        licenseEXP_edit.setText(Constants.record.get(fields[5]));
        
        licenseClass_edit = (EditText)findViewById(R.id.licenseClass_edit);
        licenseClass_edit.setText(Constants.record.get(fields[6]));
       
        save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(DriverEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				if(intent.equalsIgnoreCase("edit"))
				{
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = firstName_edit.getText().toString();
					new_record[2] = lastName_edit.getText().toString();
					new_record[3] = plateNO_edit.getText().toString();
					new_record[4] = licenseNO_edit.getText().toString();
					new_record[5] = licenseEXP_edit.getText().toString();
					new_record[6] = licenseClass_edit.getText().toString();
					Log.v("ADP", "ContactEditor - Edit Request");
//					String[] tmp = new String[2];
//					int count = 0;
//					for(int i = 0; i < Constants.db.getTable(tableName).getFields().length; i++)
//					{
//						if(Constants.db.getTable(tableName).getFieldsInView().get(0).equalsIgnoreCase(Constants.db.getTable(tableName).getFields()[i]) || Constants.db.getTable(tableName).getFieldsInView().get(1).equalsIgnoreCase(Constants.db.getTable(tableName).getFields()[i]))
//						{
//							tmp[count] = new_record[i];
//							count++;
//							Log.v("ADP", new_record[i]);
//						}
//					}
//					IntelliSyncActivity.ss.changeRecordAt(index, tmp);
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						new_record[0] = firstName_edit.getText().toString();
						new_record[1] = lastName_edit.getText().toString();
						new_record[2] = plateNO_edit.getText().toString();
						new_record[3] = licenseNO_edit.getText().toString();
						new_record[4] = licenseEXP_edit.getText().toString();
						new_record[5] = licenseClass_edit.getText().toString();
						Log.v("ADP", "DriverEditor.class - Insert Request");
					}
				
				DriverEditor.this.finish();
				engineIntent = new Intent(DriverEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				startActivity(engineIntent);
			}
			
		});     
       
        }
        
       
}