package edu.sru.distributedprocessing.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.R.id;
import edu.sru.distributedprocessing.R.layout;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tools.FileManager;

public class NavigationMain extends Activity implements OnClickListener
{
	private GridView gridview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(this, this));
        
        if(!TCPClient.isConnected)
        {
	        try
	        {
	        	//read config file to authenticate
	        	FileManager.readConfigFile(NavigationMain.this);
	        }
	        catch(Exception e)
	        {
	        	//error
	        }
        }
    }

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
            		Toast.makeText(NavigationMain.this, "Driver Selected", Toast.LENGTH_SHORT).show();
            		tablename = "drivers";
    			}else
        			if(v.getTag().equals(3))
        			{
                		Toast.makeText(NavigationMain.this, "Vehicle Type Selected", Toast.LENGTH_SHORT).show();
        				tablename = "vehicletype";
        			}else
        				if(v.getTag().equals(4))
                			{
                				//depot
                        		Toast.makeText(NavigationMain.this, "Depot Selected", Toast.LENGTH_SHORT).show();
                        		tablename = "depots";
                			}
    	
    	engineIntent.putExtra("TableName", tablename);
    	startActivity(engineIntent);
	}
    
}