package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.optionslist.Options;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
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

public class IntelliSyncActivity extends Activity implements View.OnClickListener
{    
    private String type, tableName; //navigation type, tablename
    public static ShippingScreen ss; //shipping screen list
    private static Handler handler;
    
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
        for(int i = 0; i < Constants.db.getTables().length; i++)
        {
        	 if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
             {
             	ss = new ShippingScreen(this, Constants.db.getTables()[i]);
             	tableName = Constants.db.getTables()[i].getTableName();
             	ss.Initialize(); //initialize some stuff
             }
        }
        
    }
    
//    public static void refresh() {
//    	Log.v("ADP", "IntelliSyncActivity.class - Refreshing...");
//        handler.postDelayed(new Runnable() {
//        	public void run() {
//				ss.Initialize(); // this is where you put your refresh code
//			}
//             }, 500);
//        Log.v("ADP", "IntelliSyncActivity.class - Done Refreshing");
//    }
    
    public static void changeRecordAt(final int index, final String[] inView) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.changeRecordAt(index, inView);
			}
		}, 500);
	}
    
    public static void deleteRecordAt(final String index) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.deleteRecordAt(index);
			}
		}, 500);
	}
    
    public static void insertRecordAt(final String index, final String[] record) {
		handler.postDelayed(new Runnable() {
			public void run(){
				ss.insertRecordAt(index, record);
			}
		}, 500);
	}
    
    /*
     * Method that handles different views onclick methods if set in xml layout
     * Used to find which button was clicked and handle accordingly
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
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) 
    {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) 
    {
    	Intent engineIntent = null;
		
    	switch (item.getItemId()) 
		{
		case R.id.options_menu_item:
			engineIntent = new Intent(IntelliSyncActivity.this, Options.class);
			engineIntent.putExtra("Type", type);
			startActivity(engineIntent);
			IntelliSyncActivity.this.finish();
			break;
			
	    case R.id.new_record_item:
			//open up a new record creator
	    	//Get Record Type
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
				engineIntent.putExtra("Fields", Constants.db.getTable(tableName).getFields());
				engineIntent.putExtra("Intent", "insert");
				IntelliSyncActivity.this.startActivity(engineIntent);
				IntelliSyncActivity.this.finish();
				Log.v("ADP", "CustomDialogListView.class - Insert Record");        		
			}catch(Exception e)
			{
				Log.v("ADP", "CustomDialogListView.class - Error Inserting New Record");
			}
			
			break;
			
    	}
		return true;
	}
}