package edu.sru.distributedprocessing.tools;

import java.util.ArrayList;
import java.util.HashMap;

import edu.sru.distributedprocessing.tableobjects.DataBase;

public class Constants 
{	
	//keep track of DB
	public static DataBase db = new DataBase("DataBase");
	
	//single record
	public static HashMap<String, String> record = new HashMap<String, String>();
	
	public static ArrayList<ArrayList<String>> vehicleEditorItems = new ArrayList<ArrayList<String>>();
	//vehicleEditorItems[0] = driver_group;
	//vehicleEditorItems[1] = vehicle_type_group;
	//vehicleEditorItems[2] = depot_group;
}
