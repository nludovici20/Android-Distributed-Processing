package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.dialogs.AuthenticationDialog;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
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
        
        AuthenticationDialog authenticate = new AuthenticationDialog(this, R.style.CustomDialogTheme);
        authenticate.show();
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(this, this));
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
        				tablename = "vehicle type";
        			}else
            			if(v.getTag().equals(4))
            			{
            				//warehouse
                    		Toast.makeText(NavigationMain.this, "Warehouse Selected", Toast.LENGTH_SHORT).show();
                    		tablename = "warehouses";
            			}else
                			if(v.getTag().equals(5))
                			{
                				//depot
                        		Toast.makeText(NavigationMain.this, "Depot Selected", Toast.LENGTH_SHORT).show();
                        		tablename = "depots";
                			}
    	
    	engineIntent.putExtra("TableName", tablename);
    	startActivity(engineIntent);
	}
    
}