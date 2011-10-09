package edu.sru.distributedprocessing.tableobjects;

import java.util.ArrayList;

import android.content.Context;
import edu.sru.distributedprocessing.tools.FileManager;

public class DataBase {
	private String db_name;
	private ArrayList<Table> dbTables;
	private Table[] tables;
	
	public DataBase(String dbName)
	{
		this.db_name = dbName;
		dbTables = new ArrayList<Table>();
	}
	
	public void addTable(Table table)
	{
		dbTables.add(table);
		tables = dbTables.toArray(new Table[1]);
	}
	
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
	
	public Table[] getTables()
	{
		return tables;
	}
	
	public String getDBName()
	{
		return this.db_name;
	}
	
	public void saveDB(Context context)
	{
		FileManager.saveTextFile(context, tables);			
	}
	
}
