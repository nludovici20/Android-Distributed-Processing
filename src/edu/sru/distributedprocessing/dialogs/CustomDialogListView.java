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

/**
 * This dialog class provides the user with two options when selecting a record from the list: 
 * Edit Record, New Record
 * 
 * @author Nick Ludovici
 */
public class CustomDialogListView extends Dialog 
{
    Activity activity;	/** current activity in view **/
    Table table; /** table record was selected from **/
    int recordIndex; /** record id value **/
    int listIndex; /** list view id value **/
    Intent engineIntent;
    
    /**
     * A basic constructor used to handle when a user clicks on an item from the list view.
     * 
     * @param act the Current Activity in view to pop up the dialog.
     * @param theme the theme of the menu located in the res/values/styles.xml file.
     * @param type the current table that is being viewed by the user.
     * @param recordIndex the current ID of the record that was selected.
     * @param listIndex the physical location in the list view of the record that was selected.
     */
    public CustomDialogListView(Activity act, int theme, Table type, int recordIndex, int listIndex) 
    {
        super(act, theme);
        this.activity = act;
        this.table = type;
        this.recordIndex = recordIndex; 
        this.listIndex = listIndex;
    }
    
    /**
     * This method is called upon the dialogs creation.
     * Sets up the dialog for the users viewing, including finding all attributes corresponding to the views layout.
     */
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
    				
    				/***** Attributes Passed into the next Activity *****/
    				i.putExtra("Name", table.getTableName());
    				i.putExtra("Index", recordIndex);
    				i.putExtra("ListIndex", listIndex);
    				/***** End Attributes Passed into the next Activity *****/
    				
    				activity.startActivity(i); //start the next activity
    				activity.finish(); //kill the current activity
    			 }
    			else 
    				if(clicked.equalsIgnoreCase(list[1]))
    				{
    					//Delete Record selected
    					Authenticate.tcp.sendDeleteRequest(table.getTableName(), recordIndex);
    					Log.v("ADP", "CustomDialogListView.class - Delete Record: " + table.getTableName() + " Index of Record to delete: " + recordIndex);
    				}
    		
        		dismissCustomDialog(); //close the dialog
           	}
        });
        
    }
    
    /**
     * Method called to close the dialog.
     */
    private void dismissCustomDialog()
    {
    	this.dismiss();
    }
    
   

}