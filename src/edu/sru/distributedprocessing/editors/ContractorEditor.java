package edu.sru.distributedprocessing.editors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.InsertLoading;
import edu.sru.distributedprocessing.tools.Constants;

/**
 * The ContractorEditor class is used to Edit Contractor Records.
 * This class is only called if a user wishes to edit a record from the corresponding table.
 * 
 * @author Nick Ludovici
 */
public class ContractorEditor extends Activity {
	private String tableName, intent; //tablename and intent -> edit/insert
	private TextView header; /** the contractor editor header sentence **/
	private EditText lastName_edit, firstName_edit, middleInitial_edit, primaryPhone_edit, workPhone_edit;
	private Button save_btn; /** sends the changes, or newly inserted record to the server **/
	private Intent engineIntent;
	private String[] fields, new_record;
	
    /**
     * This method is called when the activity is first created.
     * The layout's xml file is read, and all objects in the layout are initiated.
     * Any information passed in from the previous Activity is handled.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor_editor);
       
        tableName = "contractors";
        intent = getIntent().getExtras().getString("Intent");
        //index = getIntent().getExtras().getInt("Index");
       
        fields = getIntent().getExtras().getStringArray("Fields");
        new_record = new String[fields.length];
        
        /*** Editor Items ***/
        header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        lastName_edit = (EditText)findViewById(R.id.lastName_edit);
        lastName_edit.setText(Constants.record.get(fields[1]));
        
        firstName_edit = (EditText)findViewById(R.id.firstName_edit);
        firstName_edit.setText(Constants.record.get(fields[2]));
        
        middleInitial_edit = (EditText)findViewById(R.id.middleInitial_edit);
        middleInitial_edit.setText(Constants.record.get(fields[3]));
        
        primaryPhone_edit = (EditText)findViewById(R.id.primaryPhone_edit);
        primaryPhone_edit.setText(Constants.record.get(fields[4]));
        
        workPhone_edit = (EditText)findViewById(R.id.workPhone_edit);
        workPhone_edit.setText(Constants.record.get(fields[5]));
        /*** End Editor Items ***/
        
       save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if(intent.equalsIgnoreCase("edit"))
				{
					//Pull Items for sending an Edit request
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = lastName_edit.getText().toString();
					new_record[2] = firstName_edit.getText().toString();
					new_record[3] = middleInitial_edit.getText().toString();
					new_record[4] = primaryPhone_edit.getText().toString();
					new_record[5] = workPhone_edit.getText().toString();
					Log.v("ADP", "ContractorEditor - Edit Request");
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						//pull items for insert request (all but ID)
						new_record[0] = lastName_edit.getText().toString();
						new_record[1] = firstName_edit.getText().toString();
						new_record[2] = middleInitial_edit.getText().toString();
						new_record[3] = primaryPhone_edit.getText().toString();
						new_record[4] = workPhone_edit.getText().toString();
						Log.v("ADP", "ContractorEditor.class - Insert Request");
					}
				
				ContractorEditor.this.finish(); //finish editor activity
				engineIntent = new Intent(ContractorEditor.this, InsertLoading.class); 
				
				/*** Items to send to next Activity ***/
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				
				startActivity(engineIntent); //start next activity
			}
			
		});
       
        }
        
       
}