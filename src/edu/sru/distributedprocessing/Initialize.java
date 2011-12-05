/*
 * Class that creates the database structure:
 * Database: (contains the Tables)
 * Tables: Vehicle, Vehicle Type, Driver, Contractor, and Depots
 * Fields (both local and DB names)
 */

package edu.sru.distributedprocessing;

import java.io.IOException;

import android.app.Activity;
import android.util.Log;
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
		/*
		 * Instantiates both the local field values used in the GUI,
		 * Along with the server's database fields for sending requests
		 */
		contractorFields = new String[] { "ID", "Last Name", "First Name", "Middle Initial", "Primary Phone", "Work Phone" };
		dbContractorFields = new String[] { "idContractors", "LastName", "FirstName", "MiddleInitial", "PrimaryPhone", "WorkPhone" };
		
		depotFields = new String[] { "ID", "Depot Name", "Depot Address", "City", "State", "Zip Code", "Latitude", "Longitude" };
		dbDepotFields = new String[] { "idDepots", "Name", "Address", "City", "State", "ZipCode", "Latitude", "Longitude" };
		
		driverFields = new String[] { "ID", "Last Name", "First Name", "Middle Initial", "Primary Phone", "Work Phone", "License Number", "License Expiration", "License Class" };
		dbDriverFields = new String[] { "idDrivers", "LastName", "FirstName", "MiddleInitial", "PrimaryPhone", "WorkPhone", "LicenseNumber", "LicenseExpiration", "LicenseClass" };
		
		vehicleTypeFields = new String[] {  "ID", "Vehicle Type", "Sub Type", "Model", "Max Weight", "Max Range", "Length" };
		dbVehicleTypeFields = new String[] { "idVehicleType", "Type", "SubType", "Model", "MaxWeight", "MaxRange", "Length" };
		
		vehicleFields = new String[] { "ID", "License Plate Number", "Vin Number", "Manufactured Year", "Vehicle Type", "Driver", "Depot", "Available?" };
		dbVehicleFields = new String[] { "idVehicles", "PlateNumber", "VINNumber", "ManufacturedYear", "VehicleType", "Driver", "Depot", "Available" };
				
		/*
		 * Initialized tables used
		 * Input: tablename, local fields, database fields, record type, group name
		 */
		contractor_table = new Table("contractors", contractorFields, dbContractorFields, "ContractorType", "Contractors");
		depot_table = new Table("depots", depotFields, dbDepotFields, "DepotType", "Depots");
		driver_table = new Table("drivers",driverFields, dbDriverFields, "DriverType", "Drivers");
		vehicle_type_table = new Table("vehicletype", vehicleTypeFields, dbVehicleTypeFields, "VehicleTypeType", "Vehicle Types");
		vehicle_table = new Table("vehicles", vehicleFields, dbVehicleFields, "VehicleType", "Vehicles");
	
				
		/*
		 * Add the created tables to the Database
		 */
		Constants.db.addTable(vehicle_table);
		Constants.db.addTable(driver_table);
		Constants.db.addTable(depot_table);
		Constants.db.addTable(vehicle_type_table);
		Constants.db.addTable(contractor_table);
		
		
		/*
		 * If the database file exists
		 * read in the attributes and set to the appropriate table
		 * Attributes: Fields in View, Starting index
		 */
		try
		{
			FileManager.readTextFile(act);
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.v("ADP", "Initialize.class - Error reading file");
		}
	}
}
