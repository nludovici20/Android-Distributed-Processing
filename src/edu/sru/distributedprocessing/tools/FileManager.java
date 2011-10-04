package edu.sru.distributedprocessing.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import edu.sru.distributedprocessing.tableobjects.ContactRecord;
import edu.sru.distributedprocessing.tableobjects.DepotRecord;
import edu.sru.distributedprocessing.tableobjects.DriverRecord;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tableobjects.VehicleTypeRecord;

public class FileManager {
	
	public static void readTextFile(Record[] records, Context context, String filename)
	{
		String line;
		String temp[];
		int count = 0;
		// try opening the myfilename.txt
		try 
		{
			// open the file for reading
			FileInputStream fis = context.openFileInput(filename);
						
			// if file the available for reading
			if (fis != null)
			{
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(inputreader);
				
				// read every line of the file into the line-variable, on line at the time
				while (( line = buffreader.readLine()) != null)
				{
					// do something with the settings from the file
					temp = line.split("\t");
					if(records[count].getRecordType().equalsIgnoreCase("Vehicle"))
					{
						records[count] = new VehicleRecord(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString(), temp[6].toString(), true);
						count++;		
					}else
						if(records[count].getRecordType().equalsIgnoreCase("Driver"))
						{
							records[count] = new DriverRecord(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString(), temp[6].toString());
							count++;
						}else
							if(records[count].getRecordType().equalsIgnoreCase("Contact"))
							{
								records[count] = new ContactRecord(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString());
								count++;
							}else
								if(records[count].getRecordType().equalsIgnoreCase("Depot"))
								{
									records[count] = new DepotRecord(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString(), temp[6].toString(), temp[7].toString());
									count++;
								}else
									if(records[count].getRecordType().equalsIgnoreCase("Vehicle Type"))
									{
										records[count] = new VehicleTypeRecord(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString(), temp[6].toString());
										count++;
									}
				}
			}
			// close the file again
			fis.close();
			Log.v("Distributed-Processing", "Successfully read from file");
		}
		catch (Exception e)
		{
			//For now create some stuff - Really should pull from database
			ArrayList<Record> vehicles, drivers, contacts, depots, vehicle_types;
			
			//Temp Vehicle Records
			vehicles = new ArrayList<Record>();
			vehicles.add(new VehicleRecord("1", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true));
			vehicles.add(new VehicleRecord("2", "KD8-2GX", "56JFBNWUMNSJMWJ6", "1995", "Flatbed", "Niece, Sue","Garage A3", true));
			Constants.vehicle_table.addRecords(vehicles.toArray(new Record[1]));
			Constants.vehicle_table.saveTable(context);
			
			//Temp Driver Records
			drivers = new ArrayList<Record>();
			drivers.add(new DriverRecord("1", "Niece", "Sue", "KD8-2GX", "384-374", "2010-05-23", "A"));
			drivers.add(new DriverRecord("2", "Wolfe", "Shane", "HHG-JIN", "768-911", "2012-10-11", "C"));
			Constants.driver_table.addRecords(drivers.toArray(new Record[1]));
			Constants.driver_table.saveTable(context);
			
			//Temp Contact Records
			contacts = new ArrayList<Record>();
			contacts.add(new ContactRecord("1", "Adams", "Greg", "J", "5558974625", "5551234567"));
			contacts.add(new ContactRecord("2", "Johnson", "Bob", "F", "5557894561", "5551234556"));
			Constants.contact_table.addRecords(contacts.toArray(new Record[1]));
			Constants.contact_table.saveTable(context);
			
			//Temp Depot Records
			depots = new ArrayList<Record>();
			depots.add(new DepotRecord("1", "East Depot", "213 Navy Rd.", "Pittsburgh", "PA", "15232", "34", "45"));
			depots.add(new DepotRecord("2", "West Depot", "44 Martin Rd.", "Cleveland", "OH", "22357", "32", "44"));
			Constants.depot_table.addRecords(depots.toArray(new Record[1]));
			Constants.depot_table.saveTable(context);
			
			//Temp Vehicle Type Records
			vehicle_types = new ArrayList<Record>();
			vehicle_types.add(new VehicleTypeRecord("1", "Flatbed", "Open", "FL-20134", "40000", "400", "95"));
			vehicle_types.add(new VehicleTypeRecord("2", "Flatbed", "Closed", "M-DJ48DC", "40000", "400", "88"));
			Constants.vehicle_type_table.addRecords(vehicle_types.toArray(new Record[1]));
			Constants.vehicle_type_table.saveTable(context);
			
			Log.v("Distributed-Processing", "File not found");
			
		}
		
	}
}
