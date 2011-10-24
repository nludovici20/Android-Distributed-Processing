package edu.sru.distributedprocessing.tools;

import java.util.HashMap;

import edu.sru.distributedprocessing.tableobjects.DataBase;

public class Constants 
{	
	//keep track of DB
	public static DataBase db = new DataBase("DataBase");
	
	//single record
	public static HashMap<String, String> record = new HashMap<String, String>();
}
