package edu.sru.distributedprocessing.dialogs;

import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.tableobjects.Field;
import edu.sru.distributedprocessing.tableobjects.Record;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogListView extends Dialog 
{
    Activity activity;	//current activity
    Record type;
    
    public CustomDialogListView(Activity act, int theme, Record type) 
    {
        super(act, theme);
        this.activity = act;
        this.type = type;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_popup);
        
        final ListView lst=(ListView)findViewById(R.id.myList);
        
        //List of options
        String[] list = {"New Group","Edit Group", "Delete Group"};
        
        lst.setAdapter(new ArrayAdapter<String>(activity,R.layout.custom_popup_row, list));      
        
        //find out which option was selected
        lst.setOnItemClickListener(new OnItemClickListener() 
        {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
        	{
        		String clicked = ((TextView) v).getText().toString();
        		Log.v("Distributed-Process", clicked);
        		if(clicked.equalsIgnoreCase("New Group"))
        		{
        			
        		}else 
        			if(clicked.equalsIgnoreCase("Edit Group"))
        			{
        				Intent engineIntent = new Intent(activity, VehicleEditor.class);
        				
        				//send the vehicle item to the editor
        				for(int i = 0; i < type.getFields().length; i++)
        				{
        					engineIntent.putExtra(type.getFields()[i].toString(), type.getField(type.getFields()[i].toString()).getValue());        					
        				}
        				engineIntent.putExtra("Fields", type.getFields());
        				activity.startActivity(engineIntent);
        			}else 
        				if(clicked.equalsIgnoreCase("Delete Group"))
        				{
        					
        				}
        		
        		dismissCustomDialog();
           	}
        	});
        
    }
    
    //close the dialog
    private void dismissCustomDialog()
    {
    	this.dismiss();
    }
    
   

}