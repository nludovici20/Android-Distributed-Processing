package edu.sru.distributedprocessing;

import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//declaration of table(s), field(s), and TCP Client
	private Table vehicle_table, driver_table, shipment_table, routing_table, contractor_table, depot_table, warehouse_table,
				  vehicle_type_table, maintenance_table, technician_table, contact_table, report_table;
	public static TCPClient tcp;
	static String[] contactFields, dbContactFields, depotFields, dbDepotFields, driverFields, dbDriverFields, vehicleTypeFields, dbVehicleTypeFields, vehicleFields, dbVehicleFields;
	
	public Initialize(Activity act)
	{		
		
		//initialize a new Client and connect with server
		try
		{
			tcp = new TCPClient("192.168.1.3", 4004); //connect to server
			tcp.start(); //start the thread
			tcp.send("Hello Server, From Client"); //send initial message to server
		}catch (Exception e)
		{
			Log.d("ADP", "ERROR CONNECTING");
		}
		
		//different fields available for tables
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
				
		//initialize tables
		contact_table = new Table("contacts", contactFields, dbContactFields, "ContactType", "Contacts");
		depot_table = new Table("depots", depotFields, dbDepotFields, "DepotType", "Depots");
		driver_table = new Table("drivers",driverFields, dbDriverFields, "DriverType", "Drivers");
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
			//read starting info from text file
			FileManager.readTextFile(act);
		}catch(Exception e)
		{
			Log.v("ADP", "Initialize.class - Error reading file");
		} 
	}
}
