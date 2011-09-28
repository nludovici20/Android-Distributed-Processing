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
import edu.sru.distributedprocessing.tableobjects.VehicleRecord;
import edu.sru.distributedprocessing.tools.Constants;
import edu.sru.distributedprocessing.tools.FileManager;

public class Initialize 
{
	//some generic data
	public VehicleRecord[] vehicles = new VehicleRecord[2];
	
	public Initialize(Context context)
	{		
		FileManager.readTextFile(vehicles, context, Constants.vehicle_table.getTableName()+".txt");			
	}
	
	
}
