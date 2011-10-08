package edu.sru.distributedprocessing.optionslist;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
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
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tools.Constants;
import android.util.Log;

public class Options extends ExpandableListActivity
{
	private ArrayList<String> groupNames;
	private ArrayList<ArrayList<FieldOption>> field_options;
	private ArrayList<FieldOption> fields;
	private FieldOptionAdapter expListAdapter;
	private EditText index;
	private String type;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.options_main);
	    
	    
	    type = getIntent().getExtras().getString("Type");
	    index = (EditText) findViewById(R.id.starting_index);
	    if(type.equalsIgnoreCase("VehicleType"))
        {
	    	try
	    	{
	    		index.setText(""+Constants.vehicle_table.getIndex());
	    	}catch(Exception e)
	    	{
	    		//nothing
	    	}
	    	Log.v("Distributed-Processing", "" + Constants.vehicle_table.getIndex());
        }else
        	if(type.equalsIgnoreCase("DriverType"))
        	{
        		try
    	    	{
    	    		index.setText(""+Constants.driver_table.getIndex());
    	    	}catch(Exception e)
    	    	{
    	    		//nothing
    	    	}            	
    	    	Log.v("Distributed-Processing", "" + Constants.driver_table.getIndex());
            }else
            	if(type.equalsIgnoreCase("DepotType"))
            	{
            		try
        	    	{
        	    		index.setText(""+Constants.depot_table.getIndex());
        	    	}catch(Exception e)
        	    	{
        	    		//nothing
        	    	}                
        	    	Log.v("Distributed-Processing", "" + Constants.depot_table.getIndex());
                }else
                	if(type.equalsIgnoreCase("VehicleTypesType"))
                	{
                		try
            	    	{
            	    		index.setText(""+Constants.vehicle_type_table.getIndex());
            	    	}catch(Exception e)
            	    	{
            	    		//nothing
            	    	}          
            	    	Log.v("Distributed-Processing", "" + Constants.vehicle_type_table.getIndex());
                    }else
                    	if(type.equalsIgnoreCase("ContactType"))
                    	{
                    		try
                	    	{
                	    		index.setText(""+Constants.contact_table.getIndex());
                	    	}catch(Exception e)
                	    	{
                	    		//nothing
                	    	}
                	    	Log.v("Distributed-Processing", "" + Constants.contact_table.getIndex());
                    	}
	    
	    
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
					Constants.driver_fields.clear();
					Constants.contact_fields.clear();
					Constants.depot_fields.clear();
					Constants.vehicle_type_fields.clear();
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
    
    @Override
    public void onBackPressed()
    {
    	if(type.equalsIgnoreCase("VehicleType"))
        {
    		Constants.vehicle_table.setStartingIndex(Integer.parseInt(index.getText().toString()));
        }else
        	if(type.equalsIgnoreCase("DriverType"))
        	{
        		Constants.driver_table.setStartingIndex(Integer.parseInt(index.getText().toString()));
            }else
            	if(type.equalsIgnoreCase("DepotType"))
            	{
            		Constants.depot_table.setStartingIndex(Integer.parseInt(index.getText().toString()));
                }else
                	if(type.equalsIgnoreCase("VehicleTypesType"))
                	{
                		Constants.vehicle_type_table.setStartingIndex(Integer.parseInt(index.getText().toString()));
                    }else
                    	if(type.equalsIgnoreCase("ContactType"))
                    	{
                    		Constants.contact_table.setStartingIndex(Integer.parseInt(index.getText().toString()));
                    	}
    	finish();
    	Intent engineIntent = new Intent(Options.this, IntelliSyncActivity.class);
    	Log.v("Distributed-Processing", type);
    	engineIntent.putExtra("Type", type);
    	startActivity(engineIntent);
    	
    }
}