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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactEditor extends Activity {
	String tableName, intent; //tablename and intent -> edit/insert
	int index;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_editor);
       
        tableName = "contacts";
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
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(ContactEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();	
				new_record[0] = Constants.record.get(fields[0]);
				new_record[1] = lastName_edit.getText().toString();
				new_record[2] = firstName_edit.getText().toString();
				new_record[3] = middleInitial_edit.getText().toString();
				new_record[4] = primaryPhone_edit.getText().toString();
				new_record[5] = workPhone_edit.getText().toString();
				if(intent.equalsIgnoreCase("insert"))
				{
					Log.v("ADP", "ContactEditor - Insert Request");
					
					Initialize.tcp.sendInsertRequest(tableName, new_record);	
				}else
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
						Initialize.tcp.sendChangeRequest(tableName, new_record);
					}
				
				ContactEditor.this.finish();
			}
			
		});
       
        }
        
       
}