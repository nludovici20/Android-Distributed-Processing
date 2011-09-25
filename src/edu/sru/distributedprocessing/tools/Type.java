package edu.sru.distributedprocessing.tools;

public abstract class Type
{
	public abstract Field getField(String fieldname);
	public abstract void setFieldValue(String fieldname, String Value);
	public abstract String[] getFields();
}
