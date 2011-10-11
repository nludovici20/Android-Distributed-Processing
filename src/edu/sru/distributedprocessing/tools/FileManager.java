package edu.sru.distributedprocessing.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;

public class FileManager {
	
	public static void readTextFile(Context context)
	{
		String line;
		int index;
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
					String[] temp = line.split("  ");
					//read starting index for each table and set accordingly
					for(int i = 0; i < Constants.db.getTables().length; i++)
					{
						if(Constants.db.getTables()[i].getTableName().equalsIgnoreCase(temp[0]))
						{
							Constants.db.getTables()[i].getFieldsInView().clear();
							Constants.db.getTables()[i].setStartingIndex(Integer.parseInt(temp[1]));
							Constants.db.getTables()[i].addField(temp[2]);
							Constants.db.getTables()[i].addField(temp[3]);
						}
					}
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
				try
				{
					out.write(tables[i].getTableName() + "  " + tables[i].getIndex() + "  " + tables[i].getFieldsInView().get(0) + "  " + tables[i].getFieldsInView().get(1));
					out.write("\r\n");
				}catch (Exception e)
				{
					try
					{
						out.write(tables[i].getTableName() + "  " + tables[i].getIndex() + "  " + tables[i].getFields()[0] + "  " + tables[i].getFields()[1]);
						out.write("\r\n");
						Log.v("Distributed-Processing", "Default write");
					}catch(Exception ex)
					{
						Log.v("Distributed-Processing", "Error write");
					}
				}
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
