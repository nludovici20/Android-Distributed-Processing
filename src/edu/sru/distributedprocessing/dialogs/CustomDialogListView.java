package edu.sru.distributedprocessing.dialogs;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContactEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomDialogListView extends Dialog 
{
    Activity activity;	//current activity
    Table type;
    int index;
    
    public CustomDialogListView(Activity act, int theme, Table type, int index) 
    {
        super(act, theme);
        this.activity = act;
        this.type = type;
        this.index = index;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_popup);
        
        final ListView lst=(ListView)findViewById(R.id.myList);
        
        //List of options
        final String[] list = {"New Record", "Edit Record", "Delete Record"};
        
        lst.setAdapter(new ArrayAdapter<String>(activity,R.layout.custom_popup_row, list));      
        
        //find out which option was selected
        lst.setOnItemClickListener(new OnItemClickListener() 
        {
        	//@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
        	{
        		String clicked = ((TextView) v).getText().toString();
        		Log.v("ADP", "CustomDialogListView.class - " + clicked);
        		Intent engineIntent = null;
        		if(clicked.equalsIgnoreCase(list[0]))
        		{
        			//new record
        			try
        			{
        				Constants.db.getTable(type.getTableName()).addRecord(new Record(""+ type.getIndex() + 101, "Jack", "Lambert"));
        				Log.v("ADP", "CustomDialogListView.class - Insert Record");        		
        			}catch(Exception e)
        			{
        				Log.v("ADP", "CustomDialogListView.class - Error Inserting New Record");
        			}
        			
        		}else	
        			if(clicked.equalsIgnoreCase(list[1]))
	    			{
	    				//Edit Record
	    				for(int i = 0; i < Constants.db.getTables().length; i++)
	    				{
	    					if (type.getTableName().equalsIgnoreCase("contacts"))
							{
	    						engineIntent = new Intent(activity, ContactEditor.class);
							}else
								if (type.getTableName().equalsIgnoreCase("depots"))
								{
									engineIntent = new Intent(activity, DepotEditor.class);
								}else
									if (type.getTableName().equalsIgnoreCase("drivers"))
									{
										engineIntent = new Intent(activity, DriverEditor.class);
									}else
										if (type.getTableName().equalsIgnoreCase("vehicle type"))
										{
											engineIntent = new Intent(activity, VehicleTypeEditor.class);
										}else
											if (type.getTableName().equalsIgnoreCase("vehicles"))
											{
												engineIntent = new Intent(activity, VehicleEditor.class);
											}
	    				}
	    				
	    				//pull in entire record from db
	    				Initialize.tcp.getRecordRequest(type.getTableName(), index);
	    				
	    				engineIntent.putExtra("Fields", type.getFields());
	    				activity.startActivity(engineIntent);
	    			 }
	    			else 
	    				if(clicked.equalsIgnoreCase(list[2]))
	    				{
	    					//Delete Record
	    					
	    					Initialize.tcp.sendDeleteRequest(type.getTableName(), index);
	    					
	    					IntelliSyncActivity.ss.deleteRecordAt(index);
	    			    						
	    					
	    					Log.v("ADP", "CustomDialogListView.class - Delete Table: " + type.getTableName() + " Index of Record to delete: " + index);
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