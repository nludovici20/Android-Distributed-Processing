package edu.sru.distributedprocessing.tableobjects;

import java.util.ArrayList;

import android.content.Context;
import edu.sru.distributedprocessing.tools.FileManager;

/**
 * This class is used to represent a database on the Android client
 * 
 * @author Nick Ludovici
 */
public class DataBase {
	private String db_name; /** the name assigned to the local database **/
	private ArrayList<Table> dbTables; /** all of the tables added to the local database **/;
	private Table[] tables;
	
	/**
	 * Constructor Method that assigns a name to the database.
	 * 
	 * @param dbName the name to be associated with the database.
	 */
	public DataBase(String dbName)
	{
		this.db_name = dbName;
		dbTables = new ArrayList<Table>();
	}
	
	/**
	 * Method that adds a table to the database.
	 * 
	 * @param table the table to be added to the database.
	 */
	public void addTable(Table table)
	{
		dbTables.add(table);
		tables = dbTables.toArray(new Table[1]);
	}
	
	/**
	 * Method that searches through the database for the specified table.
	 * 
	 * @param tableName the name of the table to find.
	 * @return the table that was requested.
	 */
	public Table getTable(String tableName)
	{
		for(int i = 0; i < tables.length; i++)
		{
			if(tables[i].getTableName().equalsIgnoreCase(tableName))
			{
				return tables[i];
			}
		}
		return null;
	}
	
	/**
	 * Method that returns all of the tables that are in the database.
	 * 
	 * @return the tables associated with the database.
	 */
	public Table[] getTables()
	{
		return tables;
	}
	
	/**
	 * Method that returns the name that has been associated with the database.
	 * 
	 * @return the name that was associated with the database.
	 */
	public String getDBName()
	{
		return this.db_name;
	}
	
	/**
	 * Method that saves the database and specific attributes to a file.
	 * 
	 * @param context the current context that was used to save the database to a file.
	 */
	public void saveDB(Context context)
	{
		FileManager.saveTextFile(context, tables);			
	}
	
}
