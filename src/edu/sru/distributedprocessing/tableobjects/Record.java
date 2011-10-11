package edu.sru.distributedprocessing.tableobjects;

public class Record
{
	private String field1, field2;
	
	public Record(String field1, String field2)
	{
		this.field1 = field1;
		this.field2 = field2;
	}
		
	public String[] getFields()
	{
		String[] temp = { field1, field2 };
		return temp;
	}
//	public abstract Field getField(String fieldname);
//	public abstract void setFieldValue(String fieldname, String Value);
//	public abstract String[] getFields();
//	public abstract String getRecordType();
//	public abstract String getGroupName();
//	public abstract String getDBFieldName(String fieldName);
	
}
