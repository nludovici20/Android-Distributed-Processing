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

public class DepotEditor extends Activity {
	private String tableName, intent;
	//private int index; 
	private String[] fields, new_record;
	private TextView header;
	private EditText depotName_edit, depotAddress_edit, city_edit, state_edit, zip_edit, latitude_edit, longitude_edit;
	private Button save_btn;
	private Intent engineIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
       tableName = "depots";
       intent = getIntent().getExtras().getString("Intent");
       //index = getIntent().getExtras().getInt("Index");
        
       fields = getIntent().getExtras().getStringArray("Fields");
       new_record = new String[fields.length];
        
       //Editor Items
       header = (TextView)findViewById(R.id.group_header);
       header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
       depotName_edit = (EditText)findViewById(R.id.depotName_edit);
       depotName_edit.setText(Constants.record.get(fields[1]));
        
       depotAddress_edit = (EditText)findViewById(R.id.depotAddress_edit);
       depotAddress_edit.setText(Constants.record.get(fields[2]));
        
       city_edit = (EditText)findViewById(R.id.city_edit);
       city_edit.setText(Constants.record.get(fields[3]));
        
       state_edit = (EditText)findViewById(R.id.state_edit);
       state_edit.setText(Constants.record.get(fields[4]));
        
       zip_edit = (EditText)findViewById(R.id.zip_edit);
       zip_edit.setText(Constants.record.get(fields[5]));
        
       latitude_edit = (EditText)findViewById(R.id.latitude_edit);
       latitude_edit.setText(Constants.record.get(fields[6]));
        
       longitude_edit = (EditText)findViewById(R.id.longitude_edit);
       longitude_edit.setText(Constants.record.get(fields[7]));
       
       save_btn.setOnClickListener(new View.OnClickListener() 
       {
    	   public void onClick(View v) {
				Toast.makeText(DepotEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	  
				if(intent.equalsIgnoreCase("edit"))
				{
					new_record[0] = Constants.record.get(fields[0]);
				    new_record[1] = depotName_edit.getText().toString();
				    new_record[2] = depotAddress_edit.getText().toString();
			        new_record[3] = city_edit.getText().toString();
			        new_record[4] = state_edit.getText().toString();
			        new_record[5] = zip_edit.getText().toString();
			        new_record[6] = latitude_edit.getText().toString();
			        new_record[7] = longitude_edit.getText().toString();
					Log.v("ADP", "DepotEditor.class - Edit Request");					
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
					    new_record[0] = depotName_edit.getText().toString();
					    new_record[1] = depotAddress_edit.getText().toString();
				        new_record[2] = city_edit.getText().toString();
				        new_record[3] = state_edit.getText().toString();
				        new_record[4] = zip_edit.getText().toString();
				        new_record[5] = latitude_edit.getText().toString();
				        new_record[6] = longitude_edit.getText().toString();
				        Log.v("ADP", "DepotEditor.class - Insert Request");
					}
				
				DepotEditor.this.finish();
				engineIntent = new Intent(DepotEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				startActivity(engineIntent);
			}
			
		});
      
     
       
        }
        
       
}