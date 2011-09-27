package edu.sru.distributedprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tableobjects.Vehicles;

public class Initialize 
{
	//some generic data
	public Vehicles[] vehicles = new Vehicles[100];
	
	public Initialize(Context context)
	{		
		//For now create some stuff - Really should pull from database
		vehicles[0] = new Vehicles("1", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true);
		vehicles[1] = new Vehicles("2", "KD8-2GX", "56JFBNWUMNSJMWJ6", "1995", "Flatbed", "Niece,Sue","Garage A3", true);
		Table vehicleTable = new Table("Vehicle Table");
		vehicleTable.addRecords(vehicles);
		vehicleTable.saveTable(context);
		Log.v("Distributed-Processing", "File not found");
			//readTextFile(vehicles, context, "Vehicle Table.txt");	
				/*
				 * Problem when a string has space in it, (I.E. McDonald, Mary) 
				 * Need different delimiters - two spaces between each? new line? etc.
				 */
	}
	
	public void readTextFile(Record[] records, Context context, String filename)
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
					temp = line.split(" ");
					records[count] = new Vehicles(temp[0].toString(), temp[1].toString(), temp[2].toString(), temp[3].toString(), temp[4].toString(), temp[5].toString(), temp[6].toString(), true);
					count++;		
				}
			}
			// close the file again
			fis.close();
			Log.v("Distributed-Processing", "Successfully read from file");
		}
		catch (Exception e)
		{
			//For now create some stuff - Really should pull from database
			vehicles[0] = new Vehicles("1", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true);
			vehicles[1] = new Vehicles("2", "KD8-2GX", "56JFBNWUMNSJMWJ6", "1995", "Flatbed", "Niece, Sue","Garage A3", true);
			Table vehicleTable = new Table("Vehicle Table");
			vehicleTable.addRecords(vehicles);
			vehicleTable.saveTable(context);
			Log.v("Distributed-Processing", "File not found");
			
		}
		
	}
}
