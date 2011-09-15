package edu.sru.distributedprocessing;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class VehicleTabWidget extends TabActivity{
	private Button vehicles_btn, drivers_btn, shipments_btn, routing_btn, contractor_btn,
	depot_btn, warehouse_btn, vehicle_type_btn, maintenance_btn, tech_btn, contact_btn, report_btn;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.vehicles_main);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, ID_Plate.class);
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("id_plateNum").setIndicator("ID/Plate #",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, Vin_Year.class);
	    spec = tabHost.newTabSpec("vinNum_year").setIndicator("Vin#/Year",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, Type_Driver.class);
	    spec = tabHost.newTabSpec("type_driver").setIndicator("Type/Driver",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Depot_Availability.class);
	    spec = tabHost.newTabSpec("depot_available").setIndicator("Depot/Availablility",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	   
	    tabHost.setCurrentTab(0);
	    
	    vehicles_btn = (Button) findViewById(R.id.vehicle_btn);
        vehicles_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent engineIntent = new Intent(VehicleTabWidget.this, VehicleTabWidget.class);
				startActivity(engineIntent);
			}
		});
        
        drivers_btn = (Button) findViewById(R.id.drivers_btn);
        drivers_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			
			}
        });
        
        shipments_btn = (Button) findViewById(R.id.shipments_btn);
        shipments_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			
			}
        });
        
        routing_btn = (Button) findViewById(R.id.routing_btn);
        routing_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        contractor_btn = (Button) findViewById(R.id.contractors_btn);
        contractor_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        depot_btn = (Button) findViewById(R.id.depots_btn);
        depot_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        warehouse_btn = (Button) findViewById(R.id.warehouses_btn);
        warehouse_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        vehicle_type_btn = (Button) findViewById(R.id.vehicle_type_btn);
        vehicle_type_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        maintenance_btn = (Button) findViewById(R.id.maintenance_btn);
        maintenance_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        tech_btn = (Button) findViewById(R.id.technicians_btn);
        tech_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        contact_btn = (Button) findViewById(R.id.contacts_btn);
        contact_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
        
        report_btn = (Button) findViewById(R.id.reports_btn);
        report_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
        });
	}
}
