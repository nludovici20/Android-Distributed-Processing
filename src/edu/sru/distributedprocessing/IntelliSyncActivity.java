package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.editors.VehicleEditor;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IntelliSyncActivity extends Activity implements View.OnClickListener
{
    private Button vehicle_btn, drivers_btn, shipments_btn, routing_btn, contractors_btn,
    			   depots_btn, warehouses_btn, vehicle_type_btn, maintenance_btn, technicians_btn, 
    			   contacts_btn, reports_btn;
    
    private String type;
    public static ShippingScreen ss;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intelli_sync_activity);  
        
        type = getIntent().getExtras().getString("Type");       

        ss = null;
        
        for(int i = 0; i < Constants.db.getTables().length; i++)
        {
        	 if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
             {
             	ss = new ShippingScreen(this, Constants.db.getTables()[i]);
             }
        }
        
        ss.Initialize();
        
        
       
        /**				Sliding Drawer Buttons				**/
        vehicle_btn = (Button) findViewById(R.id.vehicle_btn);
        drivers_btn = (Button) findViewById(R.id.drivers_btn);
        shipments_btn = (Button) findViewById(R.id.shipments_btn);
        routing_btn = (Button) findViewById(R.id.routing_btn);
        contractors_btn = (Button) findViewById(R.id.contractors_btn);
        depots_btn = (Button) findViewById(R.id.depots_btn);
        warehouses_btn = (Button) findViewById(R.id.warehouses_btn);
        vehicle_type_btn = (Button) findViewById(R.id.vehicle_type_btn);
        maintenance_btn = (Button) findViewById(R.id.maintenance_btn);
        technicians_btn = (Button) findViewById(R.id.technicians_btn);
        contacts_btn = (Button) findViewById(R.id.contacts_btn);  
        reports_btn = (Button) findViewById(R.id.reports_btn);
        
    }
    
    @Override
    public void onClick(View v)
    {
    	Toast.makeText(IntelliSyncActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
    	
    	for(int i = 0; i < Constants.db.getTables().length; i++)
    	{
    		if(v.getTag().toString().equalsIgnoreCase(Constants.db.getTables()[i].getTableName()))
    		{
    			try
    			{
	    			finish();
	    			Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
	    			Table tbl = Constants.db.getTable(v.getTag().toString());
	    			
	    			Initialize.tcp.sendDataRequest(tbl);
	    			
	    			engineIntent.putExtra("Type", tbl.getRecordType());
	    			startActivity(engineIntent);
	    			
    			}catch (Exception e)
    			{
    				//error
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