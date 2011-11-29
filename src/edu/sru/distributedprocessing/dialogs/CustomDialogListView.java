package edu.sru.distributedprocessing.dialogs;

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
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.RecordLoading;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tableobjects.Table;

public class CustomDialogListView extends Dialog 
{
    Activity activity;	//current activity
    Table type;
    int recordIndex;
    int listIndex;
    Intent engineIntent;
    
    public CustomDialogListView(Activity act, int theme, Table type, int recordIndex, int listIndex) 
    {
        super(act, theme);
        this.activity = act;
        this.type = type;
        this.recordIndex = recordIndex; 
        this.listIndex = listIndex;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_popup);
        
        final ListView lst=(ListView)findViewById(R.id.myList);
        
        //List of options
        final String[] list = { "Edit Record", "Delete Record"};
        
        lst.setAdapter(new ArrayAdapter<String>(activity,R.layout.custom_popup_row, list));      
        
        //find out which option was selected
        lst.setOnItemClickListener(new OnItemClickListener() 
        {
        	//@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
        	{
        		String clicked = ((TextView) v).getText().toString();
        		Log.v("ADP", "CustomDialogListView.class - " + clicked);
        		engineIntent = null;
        		
    			if(clicked.equalsIgnoreCase(list[0]))
    			{
    				//open record editor & pull in entire record selected
    				Intent i = new Intent(activity, RecordLoading.class);
    				i.putExtra("Name", type.getTableName());
    				i.putExtra("Index", recordIndex);
    				i.putExtra("ListIndex", listIndex);
    				activity.startActivity(i);
    				activity.finish();
    			 }
    			else 
    				if(clicked.equalsIgnoreCase(list[1]))
    				{
    					//Delete Record selected
    					Authenticate.tcp.sendDeleteRequest(type.getTableName(), recordIndex);
    					Log.v("ADP", "CustomDialogListView.class - Delete Record: " + type.getTableName() + " Index of Record to delete: " + recordIndex);
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