package edu.sru.distributedprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationMain extends Activity 
{
    private Button vehicle_btn, drivers_btn, shipments_btn, routing_btn, contractors_btn,
    			   depots_btn, warehouses_btn, vehicle_type_btn, maintenance_btn, technicians_btn, 
    			   contacts_btn, reports_btn;
    private TextView header_txt;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
              
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Intent engineIntent = new Intent(NavigationMain.this, IntelliSyncActivity.class);

            	if(position == 0)
            	{
            		//driver
            		Toast.makeText(NavigationMain.this, "Vehicle Selected", Toast.LENGTH_SHORT).show();
            		engineIntent.putExtra("Type", "VehicleType");
            		startActivity(engineIntent);
            	}else
            		if(position == 1)
            		{
            			//contact
                		Toast.makeText(NavigationMain.this, "Contact Selected", Toast.LENGTH_SHORT).show();
            			engineIntent.putExtra("Type", "ContactType");
            			startActivity(engineIntent);
            		}else
            			if(position == 2)
            			{
            				//driver
                    		Toast.makeText(NavigationMain.this, "Driver Selected", Toast.LENGTH_SHORT).show();
            				engineIntent.putExtra("Type","DriverType");
            				startActivity(engineIntent);
            			}else
                			if(position == 3)
                			{
                				//vehicle type
                        		Toast.makeText(NavigationMain.this, "Vehicle Type Selected", Toast.LENGTH_SHORT).show();
                				engineIntent.putExtra("Type", "VehicleTypesType");
                				startActivity(engineIntent);
                			}else
                    			if(position == 4)
                    			{
                    				//warehouse
                            		Toast.makeText(NavigationMain.this, "Warehouse Selected", Toast.LENGTH_SHORT).show();
                    			}else
                        			if(position == 5)
                        			{
                        				//depot
                                		Toast.makeText(NavigationMain.this, "Depot Selected", Toast.LENGTH_SHORT).show();
                        				engineIntent.putExtra("Type", "DepotType");
                        				startActivity(engineIntent);  
                        			}
            }
        });
       
    }
    
}