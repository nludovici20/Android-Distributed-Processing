package edu.sru.distributedprocessing.tableobjects;

public class Field {
	private String fieldName, value, databaseName;
	public boolean inView;
	
	public Field(String databaseName, String fieldName, String value, boolean inView)
	{
		this.databaseName = databaseName;
		this.fieldName = fieldName;
		this.value = value;
		this.inView = inView;
	}
	
	public Field(String databaseName, String fieldName, boolean value, boolean inView)
	{
		this.databaseName = databaseName;
		this.fieldName = fieldName;
		if(value = true)
		{
			this.value = "true";
		}
		 else
		 {
			 this.value = "false";
		 }
		
		this.inView = inView;
	}
	
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}
	
	public String getFieldName()
	{
		return this.fieldName;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
		
	public boolean inView()
	{
		return this.inView;
	}
	
	public String getAvailability()
	{
		return this.value;
	}
	
	public String getDBName()
	{
		return databaseName;
		
	}
}
