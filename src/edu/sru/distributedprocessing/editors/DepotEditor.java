package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DepotEditor extends Activity {
	String tableName, intent;
	int index; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "depots";
        intent = getIntent().getExtras().getString("Intent");
        index = getIntent().getExtras().getInt("Index");
        
        final String[] fields = getIntent().getExtras().getStringArray("Fields");
        final String[] new_record = new String[fields.length];
        
        //Editor Items
        final TextView header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        final EditText depotName_edit = (EditText)findViewById(R.id.depotName_edit);
        depotName_edit.setText(Constants.record.get(fields[1]));
        
        final EditText depotAddress_edit = (EditText)findViewById(R.id.depotAddress_edit);
        depotAddress_edit.setText(Constants.record.get(fields[2]));
        
        final EditText city_edit = (EditText)findViewById(R.id.city_edit);
        city_edit.setText(Constants.record.get(fields[3]));
        
        final EditText state_edit = (EditText)findViewById(R.id.state_edit);
        state_edit.setText(Constants.record.get(fields[4]));
        
        final EditText zip_edit = (EditText)findViewById(R.id.zip_edit);
        zip_edit.setText(Constants.record.get(fields[5]));
        
        final EditText latitude_edit = (EditText)findViewById(R.id.latitude_edit);
        latitude_edit.setText(Constants.record.get(fields[6]));
        
        final EditText longitude_edit = (EditText)findViewById(R.id.longitude_edit);
        longitude_edit.setText(Constants.record.get(fields[7]));
       
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(DepotEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				new_record[0] = Constants.record.get(fields[0]);
			    new_record[1] = depotName_edit.getText().toString();
			    new_record[2] = depotAddress_edit.getText().toString();
		        new_record[3] = city_edit.getText().toString();
		        new_record[4] = state_edit.getText().toString();
		        new_record[5] = zip_edit.getText().toString();
		        new_record[6] = latitude_edit.getText().toString();
		        new_record[7] = longitude_edit.getText().toString();
		        
				if(intent.equalsIgnoreCase("insert"))
				{
					Log.v("ADP", "DepotEditor.class - Insert Request");
			    	Initialize.tcp.sendInsertRequest(tableName, new_record);	
				}else
					if(intent.equalsIgnoreCase("edit"))
					{
						Log.v("ADP", "DepotEditor.class - Edit Request");
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
						Initialize.tcp.sendChangeRequest(tableName, new_record);						
					}
				
				DepotEditor.this.finish();
			}
			
		});
      
     
       
        }
        
       
}