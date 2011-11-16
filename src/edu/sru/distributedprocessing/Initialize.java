package edu.sru.distributedprocessing;

import android.app.Activity;
import android.util.Log;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//declaration of table(s), field(s), and TCP Client
	private Table vehicle_table, driver_table, contractor_table, depot_table, vehicle_type_table;
	static String[] contractorFields, dbContractorFields, depotFields, dbDepotFields, driverFields, dbDriverFields, vehicleTypeFields, dbVehicleTypeFields, vehicleFields, dbVehicleFields;
	
	public Initialize(Activity act)
	{		
		//different fields available for tables
		contractorFields = new String[] { "ID", "Last Name", "First Name", "Middle Initial", "Primary Phone", "Work Phone" };
		dbContractorFields = new String[] { "idContractors", "LastName", "FirstName", "MiddleInitial", "PrimaryPhone", "WorkPhone" };
		depotFields = new String[] { "ID", "Depot Name", "Depot Address", "City", "State", "Zip Code", "Latitude", "Longitude" };
		dbDepotFields = new String[] { "idDepots", "Name", "Address", "City", "State", "ZipCode", "Latitude", "Longitude" };
		driverFields = new String[] { "ID", "Last Name", "First Name", "Middle Initial", "Primary Phone", "Work Phone", "Vehicle Plate No.", "License Number", "License Expiration", "License Class" };
		dbDriverFields = new String[] { "idDrivers", "LastName", "FirstName", "MiddleInitial", "PrimaryPhone", "WorkPhone", "VehiclePlateNO", "LicenseNumber", "LicenseExpiration", "LicenseClass" };
		vehicleTypeFields = new String[] {  "ID", "Vehicle Type", "Sub Type", "Model", "Max Weight", "Max Range", "Length" };
		dbVehicleTypeFields = new String[] { "idVehicleType", "Type", "SubType", "Model", "MaxWeight", "MaxRange", "Length" };
		vehicleFields = new String[] { "ID", "License Plate Number", "Vin Number", "Manufactured Year", "Vehicle Type", "Driver", "Depot", "Available?" };
		dbVehicleFields = new String[] { "idVehicles", "PlateNumber", "VINNumber", "ManufacturedYear", "VehicleType", "Driver", "Depot", "Available?" };
				
		//initialize tables
		contractor_table = new Table("contractors", contractorFields, dbContractorFields, "ContractorType", "Contractors");
		depot_table = new Table("depots", depotFields, dbDepotFields, "DepotType", "Depots");
		driver_table = new Table("drivers",driverFields, dbDriverFields, "DriverType", "Drivers");
		vehicle_type_table = new Table("vehicle type", vehicleTypeFields, dbVehicleTypeFields, "VehicleTypeType", "Vehicle Types");
		vehicle_table = new Table("vehicles", vehicleFields, dbVehicleFields, "VehicleType", "Vehicles");
	
				
		//add tables to database
		Constants.db.addTable(vehicle_table);
		Constants.db.addTable(driver_table);
		Constants.db.addTable(depot_table);
		Constants.db.addTable(vehicle_type_table);
		Constants.db.addTable(contractor_table);
		
				
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
