package edu.sru.distributedprocessing.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

public class FileManager {
	
	public static void readTextFile(Context context)
	{
		String line;
		int index;
		int i = 0;
		// try opening the myfilename.txt
		try 
		{
			// open the file for reading
			FileInputStream fis = context.openFileInput(Constants.db.getDBName() + ".txt");
			// if file the available for reading
			if (fis != null)
			{
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(inputreader);
				
				// read every line of the file into the line-variable, on line at the time
				while (( line = buffreader.readLine()) != null)
				{
					index = Integer.parseInt(line);
					//read starting index for each table and set accordingly
					Constants.db.getTables()[i].setStartingIndex(index);
					i++;
				}
			}
			// close the file again
			fis.close();
			Log.v("Distributed-Processing", "Successfully read from file");
		}
		catch (Exception e)
		{
			Log.v("Distributed-Processing", "File not read");
			
		}
		
	}
	
	public static void saveTextFile(Context context, Table[] tables)
	{
		//create file here and save data
		try
		{
			OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(Constants.db.getDBName() + ".txt", context.MODE_PRIVATE));
			for(int i = 0; i < tables.length; i++)
			{
				out.write("" + tables[i].getIndex());
				out.write("\r\n");
			}
			
			out.close();
			Log.v("Distributed-Processing", "File Created Successfully");
		}
		catch(Exception e)
		{
			Log.v("Distributed-Processing", "Error creating file");
		}
	}
}
