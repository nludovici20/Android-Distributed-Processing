package edu.sru.distributedprocessing.tableobjects;

public class Field {
	private String fieldName, value;
	public boolean inView;
	
	public Field(String fieldName, String value, boolean inView)
	{
		this.fieldName = fieldName;
		this.value = value;
		this.inView = inView;
	}
	
	public Field(String fieldName, boolean value, boolean inView)
	{
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
}
