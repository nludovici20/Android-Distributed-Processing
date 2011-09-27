package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.optionslist.Options;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
                
        header_txt = (TextView) findViewById(R.id.header_txt1);
        header_txt.setText("Welcome to the Navigation Menu");
        
        vehicle_btn = (Button) findViewById(R.id.vehicle_btn);
        vehicle_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				finish();
				Intent engineIntent = new Intent(NavigationMain.this, IntelliSyncActivity.class);
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
				Toast.makeText(NavigationMain.this, "Drivers Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        shipments_btn = (Button) findViewById(R.id.shipments_btn);
        shipments_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Shipments Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        routing_btn = (Button) findViewById(R.id.routing_btn);
        routing_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Routing Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        contractors_btn = (Button) findViewById(R.id.contractors_btn);
        contractors_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Contractors Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        depots_btn = (Button) findViewById(R.id.depots_btn);
        depots_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Depots Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        warehouses_btn = (Button) findViewById(R.id.warehouses_btn);
        warehouses_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Warehouses Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        vehicle_type_btn = (Button) findViewById(R.id.vehicle_type_btn);
        vehicle_type_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Vehicle Type Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        maintenance_btn = (Button) findViewById(R.id.maintenance_btn);
        maintenance_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Maintenance Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        technicians_btn = (Button) findViewById(R.id.technicians_btn);
        technicians_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Technicians Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        contacts_btn = (Button) findViewById(R.id.contacts_btn);
        contacts_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Contacts Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
        
        reports_btn = (Button) findViewById(R.id.reports_btn);
        reports_btn.setOnClickListener(new OnClickListener()
        {
			//@Override
			public void onClick(View v) 
			{
				Toast.makeText(NavigationMain.this, "Reports Button Selected", Toast.LENGTH_SHORT).show();
			}
        });
    }
    
}