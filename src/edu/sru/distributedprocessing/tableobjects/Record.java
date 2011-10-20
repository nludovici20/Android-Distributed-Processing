package edu.sru.distributedprocessing.tableobjects;

public class Record
{
	//private String id, field1, field2;
	String id;
	String[] fields;
	
	public Record(String id, String field1, String field2)
	{
		this.id = id;
		this.fields[0] = field1;
		this.fields[1] = field2;
	}
	
	public Record(String id, String[] fields)
	{
		this.id = id;
		this.fields = fields;
	}
	
	public Record(String[] fields)
	{
		this.fields = fields;
	}
		
	
	public String[] getFields()
	{
		return fields;
	}
	
	public String getID()
	{
		return this.id;
	}
//	public abstract Field getField(String fieldname);
//	public abstract void setFieldValue(String fieldname, String Value);
//	public abstract String[] getFields();
//	public abstract String getRecordType();
//	public abstract String getGroupName();
//	public abstract String getDBFieldName(String fieldName);
	
}
