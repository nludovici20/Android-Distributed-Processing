package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.dialogs.CustomDialogListView;
import edu.sru.distributedprocessing.optionslist.Options;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleEditor extends Activity {
	String[] fields, values;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
        
        //recieve passed in field
        fields = getIntent().getExtras().getStringArray("Fields");
        values = new String[fields.length];

        
        
        
        TextView group_header = (TextView) findViewById(R.id.group_header);
        EditText vin_text = (EditText) findViewById(R.id.vin_edit);
        EditText license_text = (EditText) findViewById(R.id.license_edit);
        EditText year_text = (EditText) findViewById(R.id.year_edit); 
        CheckBox avail_box = (CheckBox) findViewById(R.id.available_box);
        
        int array_pointer = 0;
        for(int i = 0; i < fields.length; i++)
        {
        	if(fields[i].toString().equalsIgnoreCase("ID"))
        	{
        		values[array_pointer] = getIntent().getExtras().get("ID").toString();
        		group_header.setText(group_header.getText().toString() + " " + values[array_pointer].toString());   
        		array_pointer++;
        	}
        	if(fields[i].toString().equalsIgnoreCase("Vin Number"))
        	{
        		values[array_pointer] = getIntent().getExtras().get("Vin Number").toString();
        		vin_text.setText(values[array_pointer].toString());
        		array_pointer++;
        	}
        	if(fields[i].toString().equalsIgnoreCase("License Plate Number"))
        	{
        		values[array_pointer] = getIntent().getExtras().get("License Plate Number").toString();
        		license_text.setText(values[array_pointer].toString());
        		array_pointer++;
        	}
        	if(fields[i].toString().equalsIgnoreCase("Manufactured Year"))
        	{
        		values[array_pointer] = getIntent().getExtras().get("Manufactured Year").toString();
        		year_text.setText(values[array_pointer].toString());
        		array_pointer++;
        	}    
        	if(fields[i].toString().equalsIgnoreCase("Available?"))
        	{
        		values[array_pointer] = getIntent().getExtras().get("Available?").toString();
        		if(values[array_pointer].toString().equalsIgnoreCase("True"))
        		{
        			if(!avail_box.isChecked())
        				avail_box.toggle();
        		}else
        			{
        				if(avail_box.isChecked())
        					avail_box.toggle();
        			}
        		array_pointer++;
        	}
        }
        
        
        
        
    }
}