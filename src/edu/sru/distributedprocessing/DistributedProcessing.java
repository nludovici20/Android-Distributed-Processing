package edu.sru.distributedprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DistributedProcessing extends Activity {
    private Button vehicles_btn, drivers_btn, shipments_btn, routing_btn, contractor_btn,
    				depot_btn, warehouse_btn, vehicle_type_btn, maintenance_btn, tech_btn, contact_btn, report_btn;
    private TextView header_txt;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipping_main);
        
        header_txt = (TextView) findViewById(R.id.header_txt);
        
        vehicles_btn = (Button) findViewById(R.id.vehicle_btn);
        vehicles_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
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