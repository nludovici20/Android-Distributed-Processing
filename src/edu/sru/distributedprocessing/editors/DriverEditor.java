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

/**
 * The DriverEditor class is used to Edit Driver Records.
 * This class is only called if a user wishes to edit a record from the corresponding table.
 * 
 * @author Nick Ludovici
 */
public class DriverEditor extends Activity {
	private String tableName, intent;
	private String[] fields, new_record;
	private TextView header; /** the driver editor header sentence **/
	private EditText firstName_edit, lastName_edit, middleInitial_edit, primaryPhone_edit, workPhone_edit, licenseNO_edit, licenseEXP_edit, licenseClass_edit;
	private Button save_btn; /** sends the changes, or newly inserted record to the server **/
	private Intent engineIntent;
	
	/**
     * This method is called when the activity is first created.
     * The layout's xml file is read, and all objects in the layout are initiated.
     * Any information passed in from the previous Activity is handled.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_editor);
       
        tableName = "drivers";
        intent = getIntent().getExtras().getString("Intent");
        
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        /*** Editor Items ***/
        header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        firstName_edit = (EditText)findViewById(R.id.firstName_edit);
        firstName_edit.setText(Constants.record.get(fields[1]));

        lastName_edit = (EditText)findViewById(R.id.lastName_edit);
        lastName_edit.setText(Constants.record.get(fields[2]));
        
        middleInitial_edit = (EditText)findViewById(R.id.middleInitial_edit);
        middleInitial_edit.setText(Constants.record.get(fields[3]));
        
        primaryPhone_edit = (EditText)findViewById(R.id.primaryPhone_edit);
        primaryPhone_edit.setText(Constants.record.get(fields[4]));
       
        workPhone_edit = (EditText)findViewById(R.id.workPhone_edit);
        workPhone_edit.setText(Constants.record.get(fields[5]));
               
        licenseNO_edit = (EditText)findViewById(R.id.licenseNO_edit);
        licenseNO_edit.setText(Constants.record.get(fields[6]));
        
        licenseEXP_edit = (EditText)findViewById(R.id.licenseEXP_edit);
        licenseEXP_edit.setText(Constants.record.get(fields[7]));
        
        licenseClass_edit = (EditText)findViewById(R.id.licenseClass_edit);
        licenseClass_edit.setText(Constants.record.get(fields[8]));
        /*** End Editor Items ***/
       
        save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(DriverEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				if(intent.equalsIgnoreCase("edit"))
				{
					//pull items for edit request
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = firstName_edit.getText().toString();
					new_record[2] = lastName_edit.getText().toString();
					new_record[3] = middleInitial_edit.getText().toString();
					new_record[4] = primaryPhone_edit.getText().toString();
					new_record[5] = workPhone_edit.getText().toString();
					new_record[6] = licenseNO_edit.getText().toString();
					new_record[7] = licenseEXP_edit.getText().toString();
					new_record[8] = licenseClass_edit.getText().toString();
					Log.v("ADP", "ContactEditor - Edit Request");
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						//pull items for insert request
						new_record[0] = firstName_edit.getText().toString();
						new_record[1] = lastName_edit.getText().toString();
						new_record[2] = middleInitial_edit.getText().toString();
						new_record[3] = primaryPhone_edit.getText().toString();
						new_record[4] = workPhone_edit.getText().toString();
						new_record[5] = licenseNO_edit.getText().toString();
						new_record[6] = licenseEXP_edit.getText().toString();
						new_record[7] = licenseClass_edit.getText().toString();
						Log.v("ADP", "DriverEditor.class - Insert Request");
					}
				
				DriverEditor.this.finish(); //finish current activity
				engineIntent = new Intent(DriverEditor.this, InsertLoading.class);
				
				/*** Prepare Items to be sent to next Activity ***/
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				
				startActivity(engineIntent); //start next activity
			}
			
		});     
       
        }
        
       
}