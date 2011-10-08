package edu.sru.distributedprocessing.tableobjects;

import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class Table {
	private String table_name;
	private ArrayList<Record> table_data;
	private Record[] data;
	private int startingIndex;
	private int tableSize;
	
	public Table(String tableName)
	{
		this.table_name = tableName;
		this.startingIndex = 0;
		this.tableSize = 0;
	}
	
	public void addRecords(ArrayList<Record> records)
	{
		this.table_data = records;
		this.data = records.toArray(new Record[1]);
		this.tableSize = table_data.size();
	}
	
	public void saveTable(Context context)
	{
		//create file here and save data
		try
		{
			OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(table_name + ".txt",context.MODE_PRIVATE));
			for(int i = 0; i < data.length; i++)
			{
				String[] fields = data[i].getFields();
				for(int j = 0; j < fields.length; j++)
				{
					out.write(data[i].getField(fields[j]).getValue() + "\t");
				}
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
