package edu.sru.distributedprocessing.tableobjects;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.sru.distributedprocessing.tools.FileManager;

import android.content.Context;

public class Table {
	private String table_name;
	private ArrayList<Record> table_data;
	private Record[] data;
	private int startingIndex;
	private int tableSize;
	private ArrayList<String> fieldsInView;
	private int index = 0;
	private String[] dbNames, fieldNames;
	private Hashtable<String, String> dbFields;
	String recordType, groupName;
	private int tableID;
	
	public Table(String tableName, int tableID, String[] fieldNames, String[] dbNames, String recordType, String groupName)
	{
		this.table_name = tableName;
		this.tableID = tableID;
		this.startingIndex = 0;
		this.tableSize = 0;
		fieldsInView = new ArrayList<String>();
		this.dbNames = dbNames;
		this.fieldNames = fieldNames;
		dbFields = new Hashtable<String, String>();
		this.recordType = recordType;
		this.groupName = groupName;
		
		for(int i = 0; i < fieldNames.length; i++)
		{
			dbFields.put(fieldNames[i], dbNames[i]);
		}
		
		fieldsInView.add(fieldNames[0]);
		fieldsInView.add(fieldNames[1]);
	}
	
	public String[] getFields()
	{
		return this.fieldNames;
	}

	public String getRecordType()
	{
		return this.recordType;
	}
	
	public String getGroupName()
	{
		return this.groupName;
	}
	
	public String getDBName(String fieldName)
	{
		return dbFields.get(fieldName);
	}
	
	public void addRecords(ArrayList<Record> records)
	{
		this.table_data = records;
		this.data = records.toArray(new Record[1]);
		this.tableSize = table_data.size();
	}
	
	public void addRecord(Record rec)
	{
		this.table_data.add(rec);
		this.data = this.table_data.toArray(new Record[1]);
		this.tableSize = this.table_data.size();
	}
	
	public void deleteRecords()
	{
		this.table_data.clear();			
	}
	
	public void deleteRecord(int index)
	{
		this.table_data.remove(index);
	}
	public void addField(String field)
	{
		if(fieldsInView.size() < 2)
		{
			fieldsInView.add(field);
		}
	}
	
	public ArrayList<String> getFieldsInView()
	{
		return this.fieldsInView;
	}
	
	public ArrayList<Record> getTableData()
	{
		return this.table_data;
	}
	
	public Record[] getRecords()
	{
		return this.data;
	}
	
	public String getTableName()
	{
		return this.table_name;
	}
	
	public void setStartingIndex(int index)
	{
		this.startingIndex = index;
	}
	
	public int getIndex()
	{
		return this.startingIndex;
	}
	
	public int tableSize()
	{
		return this.tableSize;
	}
	
	public int getTableID()
	{
		return this.tableID;
	}
}
