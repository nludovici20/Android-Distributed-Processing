package edu.sru.distributedprocessing.tableobjects;

public abstract class Record
{
	public abstract Field getField(String fieldname);
	public abstract void setFieldValue(String fieldname, String Value);
	public abstract String[] getFields();
}
