package edu.sru.distributedprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;
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
	public VehicleRecord[] vehicles = new VehicleRecord[2];
	public ContactRecord[] contacts = new ContactRecord[2];
	public DepotRecord[] depots = new DepotRecord[2];
	public DriverRecord[] drivers = new DriverRecord[2];
	public VehicleTypeRecord[] vehicle_types = new VehicleTypeRecord[2];
	
	public Initialize(Context context)
	{		
		
		FileManager.readTextFile(vehicles, context, Constants.vehicle_table.getTableName()+".txt");
		FileManager.readTextFile(contacts, context, Constants.contact_table.getTableName()+".txt");
		FileManager.readTextFile(depots, context, Constants.depot_table.getTableName()+".txt");
		FileManager.readTextFile(drivers, context, Constants.driver_table.getTableName()+".txt");
		FileManager.readTextFile(vehicle_types, context, Constants.vehicle_type_table.getTableName()+".txt");
		
	}
	
	
}
