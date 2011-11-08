package edu.sru.distributedprocessing.editors;

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
import android.widget.EditText;
import android.widget.TextView;

public class ContractorEditor extends Activity {
	String tableName, intent; //tablename and intent -> edit/insert
	int index;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor_editor);
       
        tableName = "contractors";
        intent = getIntent().getExtras().getString("Intent");
        index = getIntent().getExtras().getInt("Index");
       
        final String[] fields = getIntent().getExtras().getStringArray("Fields");
        final String[] new_record = new String[fields.length];
        
        //Editor Items
        TextView header = (TextView)findViewById(R.id.group_header);
        header.setText(header.getText().toString() + " " + Constants.record.get(fields[0]));
        
        final EditText lastName_edit = (EditText)findViewById(R.id.lastName_edit);
        lastName_edit.setText(Constants.record.get(fields[1]));
        
        final EditText firstName_edit = (EditText)findViewById(R.id.firstName_edit);
        firstName_edit.setText(Constants.record.get(fields[2]));
        
        final EditText middleInitial_edit = (EditText)findViewById(R.id.middleInitial_edit);
        middleInitial_edit.setText(Constants.record.get(fields[3]));
        
        final EditText primaryPhone_edit = (EditText)findViewById(R.id.primaryPhone_edit);
        primaryPhone_edit.setText(Constants.record.get(fields[4]));
        
        final EditText workPhone_edit = (EditText)findViewById(R.id.workPhone_edit);
        workPhone_edit.setText(Constants.record.get(fields[5]));
                
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//Toast.makeText(ContactEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				if(intent.equalsIgnoreCase("edit"))
				{
					new_record[0] = Constants.record.get(fields[0]);
					new_record[1] = lastName_edit.getText().toString();
					new_record[2] = firstName_edit.getText().toString();
					new_record[3] = middleInitial_edit.getText().toString();
					new_record[4] = primaryPhone_edit.getText().toString();
					new_record[5] = workPhone_edit.getText().toString();
					Log.v("ADP", "ContractorEditor - Edit Request");
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
////					IntelliSyncActivity.ss.changeRecordAt(index, tmp);
				}else
					if(intent.equalsIgnoreCase("insert"))
					{
						new_record[0] = lastName_edit.getText().toString();
						new_record[1] = firstName_edit.getText().toString();
						new_record[2] = middleInitial_edit.getText().toString();
						new_record[3] = primaryPhone_edit.getText().toString();
						new_record[4] = workPhone_edit.getText().toString();
						Log.v("ADP", "ContractorEditor.class - Insert Request");
					}
				
				ContractorEditor.this.finish();
				Intent engineIntent = new Intent(ContractorEditor.this, InsertLoading.class);
				engineIntent.putExtra("TableName", tableName);
				engineIntent.putExtra("Record", new_record);
				engineIntent.putExtra("Intent", intent);
				startActivity(engineIntent);
			}
			
		});
       
        }
        
       
}