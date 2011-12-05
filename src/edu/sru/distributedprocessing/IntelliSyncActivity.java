package edu.sru.distributedprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.optionslist.Options;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tools.Constants;

/**
 * The IntelliSyncActivity class acts as the Main Activity where the list of data 
 * is pulled in from the server and displayed to a user in a list view. 
 * 
 * @author Nick Ludovici
 */
public class IntelliSyncActivity extends Activity implements View.OnClickListener
{    
    private String type, tableName; //Record type, Table Name
    public static ShippingScreen ss;
    private static Handler handler; //handler to connect TCP thread with UI thread
    
    /**
     * This method is called when the Activity is first created. 
     * Here, the Activity recieve's any objects passed in from the previous activity and populates the list view with
     * data the user has requested to view.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intelli_sync_activity);  
        
        //passed in navigation type
        type = getIntent().getExtras().getString("Type");
        
        handler  = new Handler();
        ss = null; //initialize shipping screen
        
        /*
         * loop through tables looking for corresponding type
         * initialize shipping screen with correct table
         */
        if(Constants.db == null)
        	Log.v("ADP", "DB is null");
        else if(Constants.db.getTables() == null)
        	Log.v("ADP", "getTables() return null");
        	
        for(int i = 0; i < Constants.db.getTables().length; i++)
        {
        	 if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
             {
             	ss = new ShippingScreen(this, Constants.db.getTables()[i]);
             	tableName = Constants.db.getTables()[i].getTableName();
             	ss.Initialize(); //Method that Populates the ListView with data
             }
        }        
    }

    /**
     * Method used by the TCP thread to handle a change request via UI thread
     * @param index the index of the physical list view in the Activity to be modified
     * @param inView the two Fields currently in the users view
     */
    public static void changeRecordAt(final int index, final String[] inView) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.changeRecordAt(index, inView);
			}
		}, 500);
	}
    
    /**
     * Method used by the TCP thread to handle a deletion request via UI Thread
     * @param index the index of the physical list view in the Activity to be deleted
     */
    public static void deleteRecordAt(final String index) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.deleteRecordAt(index);
			}
		}, 500);
	}
    
    
    /**
     * Method used by the TCP Thread to handle an insert request via UI Thread
     * @param index the id of the new record to be inserted to the list
     * @param record the values of the Fields currently in view of the user
     */
    public static void insertRecordAt(final String index, final String[] record) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.insertRecordAt(index, record);
			}
		}, 500);
	}
    
    /**
     * Method that handles different views onClick methods set in the intelli_sync_activity.xml layout
     * It is used to find which button's view was clicked and handle accordingly
     * 
     * @param v the view that has been clicked upon
     */
    public void onClick(View v)
    {
    	//alert user which table button was selected
    	Toast.makeText(IntelliSyncActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
    	
    	//loop through tables
    	for(int i = 0; i < Constants.db.getTables().length; i++)
    	{
    		//find which table was selected
    		if(v.getTag().toString().equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
    		{
    			try
    			{
	    			finish(); //end current activity
	    			
	    			//start a new activity
	    			Intent engineIntent = new Intent(IntelliSyncActivity.this, TableLoading.class);
	    			
	    			//pull in data from server corresponding with table	
	    			engineIntent.putExtra("TableName", v.getTag().toString()); //add navigation type
	    			startActivity(engineIntent);
	    			
    			}catch (Exception e)
    			{
    				Log.v("ADP", "IntellysincActivity.class - TCP Request");
    			}
    		}
    	}
    }
  
    /**
     * When user hits physical menu button, the Options Menu will be created
     * with the layout (options_menu.xml) from the Res/Menu folder
     * @param menu the Menu Interface provided by the Android SDK
     */
    @Override
	public boolean onCreateOptionsMenu(Menu menu) 
    {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}
    
    /**
     * This method is called to handle when an option from the Options Menu is selected.
     * 
     * @param item the MenuItem that was clicked upon.
     */
    @Override
	public boolean onOptionsItemSelected(MenuItem item) 
    {
    	Intent engineIntent = null;
		
    	//figure out which item was selected by the id given in the xml layout
    	switch (item.getItemId()) 
		{
		case R.id.options_menu_item:
			//open up the options list activity
			IntelliSyncActivity.this.finish(); //finish this activity
			engineIntent = new Intent(IntelliSyncActivity.this, Options.class);
			engineIntent.putExtra("Type", type); //pass in record type
			startActivity(engineIntent); //start Options Activity
			break;
			
	    case R.id.new_record_item:
			//open up a new record editor (depending on the table name passed in)
			for(int i = 0; i < Constants.db.getTables().length; i++)
			{
				if (tableName.equalsIgnoreCase("contractors"))
				{
					engineIntent = new Intent(IntelliSyncActivity.this, ContractorEditor.class);
				}else
					if (tableName.equalsIgnoreCase("depots"))
					{
						engineIntent = new Intent(IntelliSyncActivity.this, DepotEditor.class);
					}else
						if (tableName.equalsIgnoreCase("drivers"))
						{
							engineIntent = new Intent(IntelliSyncActivity.this, DriverEditor.class);
						}else
							if (tableName.equalsIgnoreCase("vehicle type"))
							{
								engineIntent = new Intent(IntelliSyncActivity.this, VehicleTypeEditor.class);
							}else
								if (tableName.equalsIgnoreCase("vehicles"))
								{
									engineIntent = new Intent(IntelliSyncActivity.this, VehicleEditor.class);
								}
			}
			
			//new blank record
			for(int i = 0; i < Constants.db.getTable(tableName).getFields().length; i++)
			{
				Constants.record.put(Constants.db.getTable(tableName).getFields()[i], "");
			}
			
			try
			{
				IntelliSyncActivity.this.finish(); //finish the current activity
				
				/*
				 * Pass in data to the next Activity
				 */
				engineIntent.putExtra("Fields", Constants.db.getTable(tableName).getFields());
				engineIntent.putExtra("Intent", "insert");
				
				startActivity(engineIntent); //start the new Activity
				Log.v("ADP", "CustomDialogListView.class - Insert Record");        		
			}catch(Exception e)
			{
				e.printStackTrace();
				Log.v("ADP", "CustomDialogListView.class - Error Inserting New Record");
			}
			
			break;
			
    	}
		return true;
	}
}