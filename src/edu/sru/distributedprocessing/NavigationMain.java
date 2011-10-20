package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class NavigationMain extends Activity implements OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(this, this));
    }

	@Override
	public void onClick(View v) {
		Intent engineIntent = new Intent(NavigationMain.this, IntelliSyncActivity.class);		
    	if(v.getTag().equals(0))
    	{
    		//driver
    		Toast.makeText(NavigationMain.this, "Vehicle Selected", Toast.LENGTH_SHORT).show();
    		
    		Table tbl = Constants.db.getTable("vehicles");
    		
    		Initialize.tcp.sendDataRequest(tbl);
    		    		
			engineIntent.putExtra("Type", tbl.getRecordType());
    		startActivity(engineIntent);
    	}else
    		if(v.getTag().equals(1))
    		{
    			//contact
        		Toast.makeText(NavigationMain.this, "Contact Selected", Toast.LENGTH_SHORT).show();
        			
        		Table tbl = Constants.db.getTable("contacts");
    		
        		Initialize.tcp.sendDataRequest(tbl);
        		
    			engineIntent.putExtra("Type", "ContactType");
    			startActivity(engineIntent);
    		}else
    			if(v.getTag().equals(2))
    			{
    				//driver
            		Toast.makeText(NavigationMain.this, "Driver Selected", Toast.LENGTH_SHORT).show();
            		
            		Table tbl = Constants.db.getTable("drivers");
            		
            		Initialize.tcp.sendDataRequest(tbl);
            		
    				engineIntent.putExtra("Type","DriverType");
    				startActivity(engineIntent);
    			}else
        			if(v.getTag().equals(3))
        			{
        				//vehicle type
                		Toast.makeText(NavigationMain.this, "Vehicle Type Selected", Toast.LENGTH_SHORT).show();
        				
                		Table tbl = Constants.db.getTable("vehicle type");
                		               		
                		Initialize.tcp.sendDataRequest(tbl);
                		
                		engineIntent.putExtra("Type", "VehicleTypesType");
        				startActivity(engineIntent);
        			}else
            			if(v.getTag().equals(4))
            			{
            				//warehouse
                    		Toast.makeText(NavigationMain.this, "Warehouse Selected", Toast.LENGTH_SHORT).show();
            			}else
                			if(v.getTag().equals(5))
                			{
                				//depot
                        		Toast.makeText(NavigationMain.this, "Depot Selected", Toast.LENGTH_SHORT).show();
                        		
                        		Table tbl = Constants.db.getTable("depots");
                        		
                        		Initialize.tcp.sendDataRequest(tbl);
                        		
                        		engineIntent.putExtra("Type", "DepotType");
                				startActivity(engineIntent);  
                			}
	}
    
}