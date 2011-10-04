package edu.sru.distributedprocessing.tableobjects;

import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class Table {
	String table_name;
	ArrayList<Record> table_data;
	Record[] data;
	
	public Table(String tableName)
	{
		this.table_name = tableName;
	}
	
	public void addRecords(ArrayList<Record> records)
	{
		this.table_data = records;
		this.data = records.toArray(new Record[1]);
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
}
