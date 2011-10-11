package edu.sru.distributedprocessing;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//some generic data
	private ArrayList<Record> vehicles, contacts, depots, drivers, vehicle_types;
	private Table vehicle_table, driver_table, shipment_table, routing_table, contractor_table, depot_table, warehouse_table,
				  vehicle_type_table, maintenance_table, technician_table, contact_table, report_table;
	public static TCPClient tcp;
	String[] contactFields, dbContactFields, depotFields, dbDepotFields, driverFields, dbDriverFields, vehicleTypeFields, dbVehicleTypeFields, vehicleFields, dbVehicleFields;
	
	public Initialize(Context context)
	{		
		/*
		try
		{
			tcp = new TCPClient("10.1.42.71", 4004);
			tcp.start();
			tcp.send("Hello Server, From Client");
		}catch (Exception e)
		{
			Log.d("TCP", "ERROR CONNECTING");
		}*/
		
		contactFields = new String[] { "ID", "Last Name", "First Name", "Middle Initial", "Primary Phone", "Work Phone" };
		dbContactFields = new String[] { "idContacts", "LastName", "FirstName", "MiddleInitial", "PrimaryPhone", "WorkPhone" };
		depotFields = new String[] { "ID", "Depot Name", "Depot Address", "City", "State", "Zip Code", "Latitude", "Longitude" };
		dbDepotFields = new String[] { "idDepots", "Name", "Address", "City", "State", "ZipCode", "Latitude", "Longitude" };
		driverFields = new String[] { "ID", "Last Name", "First Name", "Vehicle Plate No.", "License Number", "License Expiration", "License Class" };
		dbDriverFields = new String[] { "idDrivers", "LastName", "FirstName", "VehiclePlateNO", "LicenseNumber", "LicenseExpiration", "LicenseClass" };
		vehicleTypeFields = new String[] {  "ID", "Vehicle Type", "Sub Type", "Model", "Max Weight", "Max Range", "Length" };
		dbVehicleTypeFields = new String[] { "idVehicleType", "Type", "SubType", "Model", "MaxWeight", "MaxRange", "Length" };
		vehicleFields = new String[] { "ID", "License Plate Number", "Vin Number", "Manufactured Year", "Vehicle Type", "Driver", "Depot", "Available?" };
		dbVehicleFields = new String[] { "idVehicles", "PlateNumber", "VINNumber", "ManufacturedYear", "VehicleType", "Driver", "Depot", "Available?" };
		
		
		//create tables
		contact_table = new Table("contacts", contactFields, dbContactFields, "ContactType", "Vehicles");
		depot_table = new Table("depots", depotFields, dbDepotFields, "DepotType", "Depots");
		driver_table = new Table("drivers", driverFields, dbDriverFields, "DriverType", "Drivers");
		vehicle_type_table = new Table("vehicle type", vehicleTypeFields, dbVehicleTypeFields, "VehicleTypeType", "Vehicle Types");
		vehicle_table = new Table("vehicles", vehicleFields, dbVehicleFields, "VehicleType", "Vehicles");
	
		/*shipment_table = new Table("Shipment Table");
		routing_table = new Table("Routing Table");
		contractor_table = new Table("Contractor Table");
		warehouse_table = new Table("Warehouse Table");
		maintenance_table = new Table("Maintenance Table");
		technician_table = new Table("Technician Table");
		report_table = new Table("Report Table");*/
		
				
		//read in data from server (for now create temp data)
		
		
		//Temp Vehicle Records
		vehicles = new ArrayList<Record>();
		for (int  i = 0; i < 1000; i++)
		{
			vehicles.add(new Record("" + i + "", "DEC-DFE1"));
		}
		vehicle_table.addRecords(vehicles);
		//Constants.vehicle_table.saveTable(context);
		
		
		//Temp Driver Records
		drivers = new ArrayList<Record>();
		for (int  i = 0; i < 1000; i++)
		{
			drivers.add(new Record("" + i + "", "Niece"));
		}
		driver_table.addRecords(drivers);
		//Constants.driver_table.saveTable(context);
		
		//Temp Contact Records
		contacts = new ArrayList<Record>();
		for (int  i = 0; i < 1000; i++)
		{
			contacts.add(new Record("" + i + "", "Adams"));
		}
		contact_table.addRecords(contacts);
		//Constants.contact_table.saveTable(context);
		
		//Temp Depot Records
		depots = new ArrayList<Record>();
		for (int  i = 0; i < 1000; i++)
		{
			depots.add(new Record("" + i + "", "East Depot"));
		}
		depot_table.addRecords(depots);
		//Constants.depot_table.saveTable(context);
		
		//Temp Vehicle Type Records
		vehicle_types = new ArrayList<Record>();
		for (int  i = 0; i < 1000; i++)
		{
			vehicle_types.add(new Record("" + i + "", "Flatbed"));
		}
		vehicle_type_table.addRecords(vehicle_types);
		//Constants.vehicle_type_table.saveTable(context);
		
		
		//add tables to database
		Constants.db.addTable(vehicle_table);
		Constants.db.addTable(driver_table);
		Constants.db.addTable(depot_table);
		Constants.db.addTable(vehicle_type_table);
		Constants.db.addTable(contact_table);
		/*Constants.db.addTable(shipment_table);
		Constants.db.addTable(routing_table);
		Constants.db.addTable(contractor_table);
		Constants.db.addTable(warehouse_table);
		Constants.db.addTable(maintenance_table);
		Constants.db.addTable(technician_table);
		Constants.db.addTable(report_table);*/
		
		
		
		try
		{
			FileManager.readTextFile(context);
		}catch(Exception e)
		{
			//handle exception
		} 
	}
	
}
