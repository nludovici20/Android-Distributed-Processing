package edu.sru.distributedprocessing.optionslist;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.R.id;
import edu.sru.distributedprocessing.R.layout;
import edu.sru.distributedprocessing.tools.Constants;
import android.util.Log;

public class Options extends ExpandableListActivity
{
	private ArrayList<String> groupNames;
	private ArrayList<ArrayList<FieldOption>> field_options;
	private ArrayList<FieldOption> fields;
	private FieldOptionAdapter expListAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.options_main);
	    
	    groupNames = new ArrayList<String>();
	    	groupNames.add( "Vehicles" );    groupNames.add( "Drivers" );
		    groupNames.add( "Shipments" );   groupNames.add( "Routing" );
		    groupNames.add( "Contractors" ); groupNames.add( "Depots" );
		    groupNames.add( "Warehouses" );  groupNames.add( "Vehicle Type" );
		    groupNames.add( "Maintenance" ); groupNames.add( "Technicians" );
		    groupNames.add( "Contacts" );	 groupNames.add( "Reports" );
		    
	    field_options = new ArrayList<ArrayList<FieldOption>>(); 
	     
	     fields = new ArrayList<FieldOption>();
	     	//Vehicle Fields
			fields.add( new FieldOption( "ID", false ) );  			
			fields.add( new FieldOption( "Plate Number", false ) ); 
			fields.add( new FieldOption( "Vin Number", false ) );  	
			fields.add( new FieldOption( "Manufactured Year", false ) ); 	
			fields.add( new FieldOption( "Vehicle Type", false ) ); 	
			fields.add( new FieldOption( "Driver", false ) ); 
			fields.add( new FieldOption( "Depot", false ) ); 			
			fields.add( new FieldOption( "Available?", false ) );  
			field_options.add(fields);
		 
		fields = new ArrayList<FieldOption>();
			//Driver Fields
			fields.add( new FieldOption ( "ID", false ) );
			fields.add( new FieldOption ( "Last Name", false ) );
			fields.add( new FieldOption ( "First Name", false ) );
			fields.add( new FieldOption ( "Vehicle Plate No.", false ) );
			fields.add( new FieldOption ( "License Number", false ) );
			fields.add( new FieldOption ( "License Expiration", false ) );
			fields.add( new FieldOption ( "License Class", false ) ); 
		    field_options.add(fields);
		     
		 fields = new ArrayList<FieldOption>();
			 //Shipment Fields
		 	 field_options.add( fields );
		     
		 fields = new ArrayList<FieldOption>();
		 	//Routing Fields
		 	 field_options.add( fields );
		 
		 fields = new ArrayList<FieldOption>();
		 	//Contractors Fields
		 	field_options.add( fields );
		 
		 fields = new ArrayList<FieldOption>();
		 	//Depot Fields
			fields.add( new FieldOption ( "ID", false ) );
			fields.add( new FieldOption ( "Name", false ) );
			fields.add( new FieldOption ( "Address", false ) );
			fields.add( new FieldOption ( "City", false ) );
			fields.add( new FieldOption ( "State", false ) );
			fields.add( new FieldOption ( "Zip Code", false ) );
			fields.add( new FieldOption ( "Latitude", false ) );
			fields.add( new FieldOption ( "Longitude", false ) );
			field_options.add( fields );
			
		fields = new ArrayList<FieldOption>();
			//Warehouse Fields
			field_options.add( fields );
			
		fields = new ArrayList<FieldOption>();
			//Vehicle Type Fields
			fields.add( new FieldOption ( "ID", false ) );
			fields.add( new FieldOption ( "SubType", false ) );
			fields.add( new FieldOption ( "Model", false ) );
			fields.add( new FieldOption ( "Max Weight", false ) );
			fields.add( new FieldOption ( "Max Range", false ) );
			fields.add( new FieldOption ( "Length", false ) );
			field_options.add( fields );
			
		fields = new ArrayList<FieldOption>();
			//Maintenance Fields
			field_options.add( fields );
			
		fields = new ArrayList<FieldOption>();
			//Technician Fields
			field_options.add( fields );
			
		fields = new ArrayList<FieldOption>();
			//Contact Fields
		fields.add( new FieldOption ( "ID", false ) );
		fields.add( new FieldOption ( "Last Name", false ) );
		fields.add( new FieldOption ( "First Name", false ) );
		fields.add( new FieldOption ( "Middle Initial", false ) );
		fields.add( new FieldOption ( "Primary Phone", false ) );
		fields.add( new FieldOption ( "Work Phone", false ) );
			field_options.add( fields );
		
		fields = new ArrayList<FieldOption>();
			//Report Fields
			field_options.add( fields );
			
			expListAdapter = new FieldOptionAdapter( this,groupNames, field_options );
			setListAdapter( expListAdapter );
			
			
			/**							**/
			Button clear_all = (Button) findViewById(R.id.clear_btn);
			clear_all.setOnClickListener(new View.OnClickListener()
			{
				//@Override
				public void onClick(View v) 
				{
					//clear all (vehicles for now)
					Constants.vehicle_fields.clear();
					
					/*
					Collection<String> vehicle = Constants.vehicle_fields.values();
					Collection<String> driver = Constants.driver_fields.values();
					Collection<String> shipment = Constants.shipment_fields.values();
					Collection<String> routing = Constants.routing_fields.values();
					Collection<String> contractor = Constants.contractor_fields.values();
					Collection<String> depot = Constants.depot_fields.values();
					Collection<String> warehouse = Constants.warehouse_fields.values();
					Collection<String> contact = Constants.contact_fields.values();
					Collection<String> maintenance = Constants.maintenance_fields.values();
					Collection<String> report = Constants.report_fields.values();
					Collection<String> vehicle_type = Constants.vehicle_type_fields.values();
					Collection<String> technician = Constants.technician_fields.values();
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < vehicle.size(); i++){
						Log.v("Distributed-Processing", "Vehicle Field: " + vehicle.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < driver.size(); i++){
						Log.v("Distributed-Processing", "Driver Field: " + driver.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < shipment.size(); i++){
						Log.v("Distributed-Processing", "Shipment Field: " + shipment.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < routing.size(); i++){
						Log.v("Distributed-Processing", "Routing Field: " + routing.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < contractor.size(); i++){
						Log.v("Distributed-Processing", "Contractor Field: " + contractor.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < depot.size(); i++){
						Log.v("Distributed-Processing", "Depot Field: " + depot.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < warehouse.size(); i++){
						Log.v("Distributed-Processing", "Warehouse Field: " + warehouse.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < contact.size(); i++){
						Log.v("Distributed-Processing", "Contact Field: " + contact.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < maintenance.size(); i++){
						Log.v("Distributed-Processing", "Maintenance Field: " + maintenance.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < report.size(); i++){
						Log.v("Distributed-Processing", "Report Field: " + report.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < vehicle_type.size(); i++){
						Log.v("Distributed-Processing", "Vehicle Type Field: " + vehicle_type.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					for(int i = 0; i < technician.size(); i++){
						Log.v("Distributed-Processing", "Technician Field: " + technician.toArray()[i].toString());
					}
					Log.v("Distributed-Processing", "*****************************************************");
					*/
				}
			});
	}
 	  	 

    public void  onContentChanged  () 
    {
        super.onContentChanged();
    }

    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int fieldPosition, long id) 
    {
		CheckBox cb = (CheckBox) v.findViewById(R.id.check_box);
		final FieldOption f = (FieldOption)expListAdapter.getChild(groupPosition, fieldPosition);
			if(cb.isChecked())
			{
				cb.toggle();
				f.state = !cb.isChecked();
				if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Vehicles"))
				{
					try
					{
						Constants.vehicle_fields.remove(f.getField());
					}
					 catch(Exception e)
					 {
						//handle exception
					 }	
				}else
					if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Drivers"))
					{
						try
						{
							Constants.driver_fields.remove(f.getField());
						}
						 catch(Exception e)
						 {
							//handle exception
						 }
					}else
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Shipments"))
						{
							try
							{
								Constants.shipment_fields.remove(f.getField());
							}
							 catch(Exception e)
							 {
								//handle exception
							 }
						}else
							if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Routing"))
							{
								try
								{
									Constants.routing_fields.remove(f.getField());
								}
								 catch(Exception e)
								 {
									//handle exception
								 }
							}else
								if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Contractors"))
								{
									try
									{
										Constants.contractor_fields.remove(f.getField());
									}
									 catch(Exception e)
									 {
										//handle exception
									 }
								}else
									if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Depots"))
									{
										try
										{
											Constants.depot_fields.remove(f.getField());
										}
										 catch(Exception e)
										 {
											//handle exception
										 }
									}else
										if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Warehouses"))
										{
											try
											{
												Constants.warehouse_fields.remove(f.getField());
											}
											 catch(Exception e)
											 {
												//handle exception
											 }
										}else
											if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Vehicle Types"))
											{
												try
												{
													Constants.vehicle_type_fields.remove(f.getField());
												}
												 catch(Exception e)
												 {
													//handle exception
												 }
											}else
												if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Maintenance"))
												{
													try
													{
														Constants.maintenance_fields.remove(f.getField());
													}
													 catch(Exception e)
													 {
														//handle exception
												 	 }
												}else
													if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Contacts"))
													{
														try
														{
															Constants.contact_fields.remove(f.getField());
														}catch(Exception e){
															//handle exception
														}
													}else
														if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Reports"))
														{
															try
															{
																Constants.report_fields.remove(f.getField());
															}
															 catch(Exception e)
															 {
																//handle exception
															 }
														}
				Log.v("Distributed-Processing", groupNames.get(groupPosition).toString() + "-" + f.getField() + ": Unchecked");
			}else{
				cb.toggle();
				f.state = cb.isChecked();
				if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Vehicles"))
				{
					Constants.vehicle_fields.put(f.getField(), f.getField());
				}else
					if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Drivers"))
					{
						Constants.driver_fields.put(f.getField(), f.getField());
					}else
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Shipments"))
						{
							Constants.shipment_fields.put(f.getField(), f.getField());
						}else
							if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Routing"))
							{
								Constants.routing_fields.put(f.getField(), f.getField());
							}else
								if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Contractors"))
								{
									Constants.contractor_fields.put(f.getField(), f.getField());
								}else
									if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Depots"))
									{
										Constants.depot_fields.put(f.getField(), f.getField());
									}else
										if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Warehouses"))
										{
											Constants.warehouse_fields.put(f.getField(), f.getField());
										}else
											if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Vehicle Type"))
											{
												Constants.vehicle_type_fields.put(f.getField(), f.getField());
											}else
												if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Maintenance"))
												{
													Constants.maintenance_fields.put(f.getField(), f.getField());
												}else
													if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Technicians"))
													{
														Constants.technician_fields.put(f.getField(), f.getField());
													}else
														if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Contacts"))
														{
															Constants.contact_fields.put(f.getField(), f.getField());
														}else
															if(groupNames.get(groupPosition).toString().equalsIgnoreCase("Reports"))
															{
																Constants.report_fields.put(f.getField(), f.getField());
															}
				Log.v("Distributed-Processing", groupNames.get(groupPosition).toString() + "-" + f.getField() + ": Checked");
			}		
    	return false;
    }

    public void  onGroupExpand  (int groupPosition) 
    {
    	//which group was expanded?
    	Log.v("Distributed-Processing", groupNames.get(groupPosition).toString());
    }
}