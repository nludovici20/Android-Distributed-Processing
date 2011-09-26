package edu.sru.distributedprocessing.tableobjects;

import java.io.OutputStreamWriter;
import android.content.Context;
import android.util.Log;

public class Table {
	String table_name;
	Record[] data;
	
	public Table(String tableName)
	{
		this.table_name = tableName;
	}
	
	public void addRecords(Record[] records)
	{
		this.data = records;
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
					out.write(data[i].getField(fields[j]).getValue() + " ");
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
	
	public Record[] getRecords()
	{
		return this.data;
	}
	
}
