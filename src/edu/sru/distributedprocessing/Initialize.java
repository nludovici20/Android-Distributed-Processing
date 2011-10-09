package edu.sru.distributedprocessing;

import java.util.ArrayList;

import android.content.Context;
import edu.sru.distributedprocessing.tableobjects.ContactRecord;
import edu.sru.distributedprocessing.tableobjects.DepotRecord;
import edu.sru.distributedprocessing.tableobjects.DriverRecord;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tableobjects.VehicleTypeRecord;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//some generic data
	private ArrayList<Record> vehicles, contacts, depots, drivers, vehicle_types;
	private Table vehicle_table, driver_table, shipment_table, routing_table, contractor_table, depot_table, warehouse_table,
				  vehicle_type_table, maintenance_table, technician_table, contact_table, report_table;
	
	public Initialize(Context context)
	{				
		//create tables
		vehicle_table = new Table("Vehicle Table");
		driver_table = new Table("Driver Table");
		shipment_table = new Table("Shipment Table");
		routing_table = new Table("Routing Table");
		contractor_table = new Table("Contractor Table");
		depot_table = new Table("Depot Table");
		warehouse_table = new Table("Warehouse Table");
		vehicle_type_table = new Table("Vehicle Type Table");
		maintenance_table = new Table("Maintenance Table");
		technician_table = new Table("Technician Table");
		contact_table = new Table("Contact Table");
		report_table = new Table("Report Table");
		
				
		//read in data from server (for now create temp data)
						
		//Temp Vehicle Records
		vehicles = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			vehicles.add(new VehicleRecord("" + i + "", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true));
		}
		vehicle_table.addRecords(vehicles);
		//Constants.vehicle_table.saveTable(context);
		
		
		//Temp Driver Records
		drivers = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			drivers.add(new DriverRecord("" + i + "", "Niece", "Sue", "KD8-2GX", "384-374", "2010-05-23", "A"));
		}
		driver_table.addRecords(drivers);
		//Constants.driver_table.saveTable(context);
		
		//Temp Contact Records
		contacts = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			contacts.add(new ContactRecord("" + i + "", "Adams", "Greg", "J", "5558974625", "5551234567"));
		}
		contact_table.addRecords(contacts);
		//Constants.contact_table.saveTable(context);
		
		//Temp Depot Records
		depots = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			depots.add(new DepotRecord("" + i + "", "East Depot", "213 Navy Rd.", "Pittsburgh", "PA", "15232", "34", "45"));
		}
		depot_table.addRecords(depots);
		//Constants.depot_table.saveTable(context);
		
		//Temp Vehicle Type Records
		vehicle_types = new ArrayList<Record>();
		for (int  i = 0; i < 10000; i++)
		{
			vehicle_types.add(new VehicleTypeRecord("" + i + "", "Flatbed", "Open", "FL-20134", "40000", "400", "95"));
		}
		vehicle_type_table.addRecords(vehicle_types);
		//Constants.vehicle_type_table.saveTable(context);
		
		
		//add tables to database
		Constants.db.addTable(vehicle_table);
		Constants.db.addTable(driver_table);
		Constants.db.addTable(shipment_table);
		Constants.db.addTable(routing_table);
		Constants.db.addTable(contractor_table);
		Constants.db.addTable(depot_table);
		Constants.db.addTable(warehouse_table);
		Constants.db.addTable(vehicle_type_table);
		Constants.db.addTable(maintenance_table);
		Constants.db.addTable(technician_table);
		Constants.db.addTable(contact_table);
		Constants.db.addTable(report_table);
		
		
		
		try
		{
			FileManager.readTextFile(context);
		}catch(Exception e)
		{
			//handle exception
		}
	}
	
}
