package edu.sru.distributedprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	public ArrayList<Record> vehicles, contacts, depots, drivers, vehicle_types;
	
	public Initialize(Context context)
	{		
		vehicles = new ArrayList<Record>();
		contacts = new ArrayList<Record>();
		depots = new ArrayList<Record>();
		drivers = new ArrayList<Record>();
		vehicle_types = new ArrayList<Record>();
		
		FileManager.readTextFile(vehicles.toArray(new Record[1]), context, Constants.vehicle_table.getTableName()+".txt");
		FileManager.readTextFile(contacts.toArray(new Record[1]), context, Constants.contact_table.getTableName()+".txt");
		FileManager.readTextFile(depots.toArray(new Record[1]), context, Constants.depot_table.getTableName()+".txt");
		FileManager.readTextFile(drivers.toArray(new Record[1]), context, Constants.driver_table.getTableName()+".txt");
		FileManager.readTextFile(vehicle_types.toArray(new Record[1]), context, Constants.vehicle_type_table.getTableName()+".txt");
		
	}
	
	
}
