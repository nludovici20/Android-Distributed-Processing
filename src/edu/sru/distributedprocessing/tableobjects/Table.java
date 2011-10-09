package edu.sru.distributedprocessing.tableobjects;

import java.util.ArrayList;

import edu.sru.distributedprocessing.tools.FileManager;

import android.content.Context;

public class Table {
	private String table_name;
	private ArrayList<Record> table_data;
	private Record[] data;
	private int startingIndex;
	private int tableSize;
	private ArrayList<String> fieldsInView;;
	private int index = 0;
	
	public Table(String tableName)
	{
		this.table_name = tableName;
		this.startingIndex = 0;
		this.tableSize = 0;
		fieldsInView = new ArrayList<String>();
		
	}
	
	public void addRecords(ArrayList<Record> records)
	{
		this.table_data = records;
		this.data = records.toArray(new Record[1]);
		this.tableSize = table_data.size();
		fieldsInView.add(data[0].getFields()[0].toString());
		fieldsInView.add(data[0].getFields()[1].toString());
		
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
}
