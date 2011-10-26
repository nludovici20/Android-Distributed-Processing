package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.optionslist.Options;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class IntelliSyncActivity extends Activity implements View.OnClickListener
{    
    private String type; //navigation type
    public static ShippingScreen ss; //shipping screen list
    public static boolean canUpdate;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intelli_sync_activity);  
        
        //passed in navigation type
        type = getIntent().getExtras().getString("Type");       
        
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
             }
        }
        
        ss.Initialize(); //initialize some stuff
        
        runOnUiThread(new Runnable(){
        	@Override 
        	public void run(){
        		while(canUpdate)
        		{
        			ss.Update();
        			canUpdate = false;
        		}
        		try {
					this.wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
    }
    
    
    /*
     * Method that handles different views onclick methods if set in xml layout
     * Used to find which button was clicked and handle accordingly
     */
    @Override
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
	    			Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
	    			
	    			//pull in data from server corresponding with table
	    			Table tbl = Constants.db.getTable(v.getTag().toString());
	    			Initialize.tcp.sendTableRequest(tbl);
	    			
	    			engineIntent.putExtra("Type", tbl.getRecordType()); //add navigation type
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
    	Intent engineIntent;
		switch (item.getItemId()) 
		{
		case R.id.options_menu_item:
			engineIntent = new Intent(IntelliSyncActivity.this, Options.class);
			engineIntent.putExtra("Type", type);
			startActivity(engineIntent);
			finish();
			
	    case R.id.new_record_item:
			//open up a new record creator
    	}
		return true;
	}
}