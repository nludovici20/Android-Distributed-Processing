package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactEditor extends Activity {
	String tableName;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_editor);
       
        tableName = "contacts";
        String[] fields = getIntent().getExtras().getStringArray("Fields");
        
        //Editor Items
        TextView header = (TextView)findViewById(R.id.group_header);
        header.setText(Constants.record.get(fields[0]));
        
        EditText lastName_edit = (EditText)findViewById(R.id.lastName_edit);
        lastName_edit.setText(Constants.record.get(fields[1]));
        
        EditText firstName_edit = (EditText)findViewById(R.id.firstName_edit);
        firstName_edit.setText(Constants.record.get(fields[2]));
        
        EditText middleInitial_edit = (EditText)findViewById(R.id.middleInitial_edit);
        middleInitial_edit.setText(Constants.record.get(fields[3]));
        
        EditText primaryPhone_edit = (EditText)findViewById(R.id.primaryPhone_edit);
        primaryPhone_edit.setText(Constants.record.get(fields[4]));
        
        EditText workPhone_edit = (EditText)findViewById(R.id.workPhone_edit);
        workPhone_edit.setText(Constants.record.get(fields[5]));
        
//        EditText emailAddress_edit = (EditText)findViewById(R.id.emailAddress_edit);
//        EditText streetAdd_edit = (EditText)findViewById(R.id.streetAdd_edit);
//        EditText streetAdd2_edit = (EditText)findViewById(R.id.streetAdd2_edit);
//        EditText zip_edit = (EditText)findViewById(R.id.zip_edit);
//        EditText state_edit = (EditText)findViewById(R.id.state_edit);
//        EditText city_edit = (EditText)findViewById(R.id.city_edit);
       
        
        Button save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ContactEditor.this, "Save button clicked", Toast.LENGTH_SHORT).show();				
			}
		});
       
        }
        
       
}