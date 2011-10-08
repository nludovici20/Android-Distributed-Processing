package edu.sru.distributedprocessing;

import java.util.ArrayList;

import android.content.Context;
import edu.sru.distributedprocessing.tableobjects.ContactRecord;
import edu.sru.distributedprocessing.tableobjects.DepotRecord;
import edu.sru.distributedprocessing.tableobjects.DriverRecord;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tableobjects.VehicleTypeRecord;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//some generic data
	public ArrayList<Record> vehicles, contacts, depots, drivers, vehicle_types;
	
	public Initialize(Context context)
	{				
		FileManager.readTextFile(context, Constants.vehicle_table.getTableName()+".txt");
		FileManager.readTextFile(context, Constants.contact_table.getTableName()+".txt");
		FileManager.readTextFile(context, Constants.depot_table.getTableName()+".txt");
		FileManager.readTextFile(context, Constants.driver_table.getTableName()+".txt");
		FileManager.readTextFile(context, Constants.vehicle_type_table.getTableName()+".txt");
		
		//read in data from server (for now create temp data)
						
		//Temp Vehicle Records
		vehicles = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			vehicles.add(new VehicleRecord("" + i + "", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true));
		}
		Constants.vehicle_table.addRecords(vehicles);
		//Constants.vehicle_table.saveTable(context);
		
		
		//Temp Driver Records
		drivers = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			drivers.add(new DriverRecord("" + i + "", "Niece", "Sue", "KD8-2GX", "384-374", "2010-05-23", "A"));
		}
		Constants.driver_table.addRecords(drivers);
		//Constants.driver_table.saveTable(context);
		
		//Temp Contact Records
		contacts = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			contacts.add(new ContactRecord("" + i + "", "Adams", "Greg", "J", "5558974625", "5551234567"));
		}
		Constants.contact_table.addRecords(contacts);
		//Constants.contact_table.saveTable(context);
		
		//Temp Depot Records
		depots = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			depots.add(new DepotRecord("" + i + "", "East Depot", "213 Navy Rd.", "Pittsburgh", "PA", "15232", "34", "45"));
		}
		Constants.depot_table.addRecords(depots);
		//Constants.depot_table.saveTable(context);
		
		//Temp Vehicle Type Records
		vehicle_types = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			vehicle_types.add(new VehicleTypeRecord("" + i + "", "Flatbed", "Open", "FL-20134", "40000", "400", "95"));
		}
		Constants.vehicle_type_table.addRecords(vehicle_types);
		//Constants.vehicle_type_table.saveTable(context);
	}
	
	
}
