package edu.sru.distributedprocessing.tools;

import java.util.ArrayList;
import java.util.HashMap;

import edu.sru.distributedprocessing.tableobjects.DataBase;

/**
 * This class is a Global Class which gives access to the local database, and a single record.
 * This class allows these objects to be manipulated or updated by any of the classes that give static reference to them.
 * 
 * @author Nick Ludovici
 */
public class Constants 
{	
	/**
	 * the local database for keeping track of tables, and records.
	 */
	public static DataBase db = new DataBase("DataBase");
	
	/**
	 * a single record used in record requests
	 */
	public static HashMap<String, String> record = new HashMap<String, String>();
}
