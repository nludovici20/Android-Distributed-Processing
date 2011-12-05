package edu.sru.distributedprocessing.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tools.FileManager;

/**
 * This class is used for navigation of the tables available to the user.
 * The user is presented with 5 Images corresponding to their own table.
 * When an ImageButton is selected, the client will attempt to pull that table in for the users viewing.
 * 
 * @author Nick Ludovici
 */
public class NavigationMain extends Activity implements OnClickListener
{
	private GridView gridview;
	private boolean goBack;
	
	/**
     *  Called when the activity is first created.
     *  All objects in the layout (navigation_main.xml) are handled here.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        
        goBack = false;
        
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(this, this));
       
        //Check connection status
        if(!TCPClient.isConnected)
        {
	        try
	        {
	        	//read config file to authenticate
	        	FileManager.readConfigFile(NavigationMain.this);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	Log.v("ADP", "NavigationMain.class - Error reading config file");
	        }
        }
    }

    /**
     * Method that handles different views onClick methods set in the navigation_main.xml layout
     * It is used to find which button's view was clicked and handle accordingly
     * 
     * @param v the view that has been clicked upon
     */
	public void onClick(View v) {
		Intent engineIntent = new Intent(NavigationMain.this, TableLoading.class);		
		String tablename="";
    	if(v.getTag().equals(0))
    	{
       		Toast.makeText(NavigationMain.this, "Vehicle Selected", Toast.LENGTH_SHORT).show();
    		tablename="vehicles";
    	}else
    		if(v.getTag().equals(1))
    		{
    			//contact
        		Toast.makeText(NavigationMain.this, "Contractor Selected", Toast.LENGTH_SHORT).show();
        		tablename="contractors";
    		}else
    			if(v.getTag().equals(2))
    			{
    				//drivers
            		Toast.makeText(NavigationMain.this, "Driver Selected", Toast.LENGTH_SHORT).show();
            		tablename = "drivers";
    			}else
        			if(v.getTag().equals(3))
        			{
        				//vehicle type
                		Toast.makeText(NavigationMain.this, "Vehicle Type Selected", Toast.LENGTH_SHORT).show();
        				tablename = "vehicletype";
        			}else
        				if(v.getTag().equals(4))
                			{
                				//depot
                        		Toast.makeText(NavigationMain.this, "Depot Selected", Toast.LENGTH_SHORT).show();
                        		tablename = "depots";
                			}else
                				if(v.getTag().equals(5))
                				{
                					//Go Back
                					goBack = true;
                					
                				}
    	
    	if(!goBack)
    	{
    		engineIntent.putExtra("TableName", tablename);
    		startActivity(engineIntent); //start next activity
    	}else
    	{
    		this.finish();
    	}
	}
    
}