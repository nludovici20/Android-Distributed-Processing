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

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.R.id;
import edu.sru.distributedprocessing.R.layout;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tableobjects.Table;
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
	    
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			try
			{
				if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
				{
					index.setText(""+Constants.db.getTables()[i].getIndex());
				}
			}catch (Exception e)
			{
				//handle
			}
			Log.v("Distributed-Processing", "" + Constants.db.getTables()[i].getIndex());
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
			fields.add( new FieldOption( "License Plate Number", false ) ); 
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
					for(int i = 0; i < Constants.db.getTables().length; i++)
					{
						Constants.db.getTables()[i].getFieldsInView().clear();
					}
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
				
				for(int i = 0; i < Constants.db.getTables().length; i++)
				{
					try
					{
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase(Constants.db.getTables()[i].getGroupName()))
						{
								Constants.db.getTables()[i].getFieldsInView().remove(f.getField());
						}
					}catch(Exception e)
					 {
						//handle exception
					 }	
				}	
				Log.v("Distributed-Processing", groupNames.get(groupPosition).toString() + "-" + f.getField() + ": Unchecked");
			}else{
				cb.toggle();
				f.state = cb.isChecked();
				
				for(int i = 0; i < Constants.db.getTables().length; i++)
				{
					try
					{
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase(Constants.db.getTables()[i].getGroupName()))
						{
							if (Constants.db.getTables()[i].getFieldsInView().size() < 2)
							{
								Constants.db.getTables()[i].addField(f.getField());
							}else
							{
								Toast.makeText(this, "Two fields already selected, clear fields to add more", Toast.LENGTH_SHORT).show();
								cb.toggle();
							}
						}
					}catch(Exception e)
					{
						//handle
					}
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
    	for(int i = 0; i < Constants.db.getTables().length; i++)
		{
    		try
    		{
				if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
				{
					Constants.db.getTables()[i].setStartingIndex(Integer.parseInt(index.getText().toString()));
					Table tbl = Constants.db.getTables()[i];
//					Initialize.tcp.sendDataRequest(
//    				tbl.getTableName(), tbl.getIndex(), tbl.getDBName(tbl.getFieldsInView().get(0))
//    				,tbl.getDBName(tbl.getFieldsInView().get(1)));
					
					Initialize.tcp.sendDataRequest(tbl);
					
					//Log.d("TCP", str);
				}
    		}catch (Exception e)
    		{
    			//handle
    		}
		}
    	Constants.db.saveDB(this);
    	finish();
    	Intent engineIntent = new Intent(Options.this, IntelliSyncActivity.class);
    	Log.v("Distributed-Processing", type);
    	engineIntent.putExtra("Type", type);
    	startActivity(engineIntent);
    	
    }
}