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

public class IntelliSyncActivity extends Activity 
{
    private Button vehicle_btn, drivers_btn, shipments_btn, routing_btn, contractors_btn,
    			   depots_btn, warehouses_btn, vehicle_type_btn, maintenance_btn, technicians_btn, 
    			   contacts_btn, reports_btn;
    
    private String type;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intelli_sync_activity);  
        
        type = getIntent().getExtras().getString("Type");       

        ShippingScreen ss = null;
        
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
        vehicle_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
				
//				Table tbl = Constants.db.getTable("vehicles");
//        		String str = "\0" + tbl.getTableName() + "\0" +	tbl.getDBName(tbl.getFieldsInView().get(0)) + "\0" + tbl.getDBName(tbl.getFieldsInView().get(1)) + "\0" + + tbl.getIndex() + "\0";
//    			Initialize.tcp.send(str);
//    			Log.d("TCP", str);
				
				engineIntent.putExtra("Type", "VehicleType");
				startActivity(engineIntent);
			}
		});
        
        drivers_btn = (Button) findViewById(R.id.drivers_btn);
        drivers_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
				
//				Table tbl = Constants.db.getTable("drivers");
//        		String str = "\0" + tbl.getTableName() + "\0" +	tbl.getDBName(tbl.getFieldsInView().get(0)) + "\0" + tbl.getDBName(tbl.getFieldsInView().get(1)) + "\0" + + tbl.getIndex() + "\0";
//    			Initialize.tcp.send(str);
//    			Log.d("TCP", str);
				
				engineIntent.putExtra("Type", "DriverType");
				startActivity(engineIntent);
			}
        });
        
        shipments_btn = (Button) findViewById(R.id.shipments_btn);
        shipments_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Shipments Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        routing_btn = (Button) findViewById(R.id.routing_btn);
        routing_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Routing Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        contractors_btn = (Button) findViewById(R.id.contractors_btn);
        contractors_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Contractors Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        depots_btn = (Button) findViewById(R.id.depots_btn);
        depots_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
				
//				Table tbl = Constants.db.getTable("depots");
//        		String str = "\0" + tbl.getTableName() + "\0" +	tbl.getDBName(tbl.getFieldsInView().get(0)) + "\0" + tbl.getDBName(tbl.getFieldsInView().get(1)) + "\0" + + tbl.getIndex() + "\0";
//    			Initialize.tcp.send(str);
//    			Log.d("TCP", str);
    			
				engineIntent.putExtra("Type", "DepotType");
				startActivity(engineIntent);
			}
        });
        
        warehouses_btn = (Button) findViewById(R.id.warehouses_btn);
        warehouses_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Warehouses Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        vehicle_type_btn = (Button) findViewById(R.id.vehicle_type_btn);
        vehicle_type_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
//				
//				Table tbl = Constants.db.getTable("vehicle type");
//        		String str = "\0" + tbl.getTableName() + "\0" +	tbl.getDBName(tbl.getFieldsInView().get(0)) + "\0" + tbl.getDBName(tbl.getFieldsInView().get(1)) + "\0" + + tbl.getIndex() + "\0";
//    			Initialize.tcp.send(str);
//    			Log.d("TCP", str);
    			
				engineIntent.putExtra("Type", "VehicleTypesType");
				startActivity(engineIntent);
			}
        });
        
        maintenance_btn = (Button) findViewById(R.id.maintenance_btn);
        maintenance_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Maintenance Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        technicians_btn = (Button) findViewById(R.id.technicians_btn);
        technicians_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Technicians Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        contacts_btn = (Button) findViewById(R.id.contacts_btn);
        contacts_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(IntelliSyncActivity.this, IntelliSyncActivity.class);
				
//				Table tbl = Constants.db.getTable("contacts");
//        		String str = "\0" + tbl.getTableName() + "\0" +	tbl.getDBName(tbl.getFieldsInView().get(0)) + "\0" + tbl.getDBName(tbl.getFieldsInView().get(1)) + "\0" + + tbl.getIndex() + "\0";
//    			Initialize.tcp.send(str);
//    			Log.d("TCP", str);
    			
				engineIntent.putExtra("Type", "ContactType");
				startActivity(engineIntent);
			}
        });
        
        reports_btn = (Button) findViewById(R.id.reports_btn);
        reports_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(IntelliSyncActivity.this, "Reports Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
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