package edu.sru.distributedprocessing.tableobjects;

import java.util.ArrayList;
import java.util.Hashtable;

import android.util.Log;

/**
 * This class represents the tables located in the Database on the server.
 * The tables hold records, which hold field values.
 * 
 * @author Nick Ludovici
 */
public class Table {
	private String table_name;
	private ArrayList<Record> table_data;
	private Record[] data;
	private int startingIndex;
	private int tableSize;
	private ArrayList<String> fieldsInView;
	private int index = 0;
	private String[] dbNames, fieldNames;
	private Hashtable<String, String> dbFields;
	String recordType, groupName;
	
	/**
	 * @param tableName the name of the table being created.
	 * @param fieldNames the local field names associated with this table.
	 * @param dbNames the database field names corresponding to the local field names in this table.
	 * @param recordType the record type associated with the records in this table.
	 * @param groupName the group name associated with this table.
	 */
	public Table(String tableName, String[] fieldNames, String[] dbNames, String recordType, String groupName)
	{
		this.table_name = tableName;
		this.startingIndex = 0;
		this.tableSize = 0;
		fieldsInView = new ArrayList<String>();
		this.dbNames = dbNames;
		this.fieldNames = fieldNames;
		dbFields = new Hashtable<String, String>();
		this.recordType = recordType;
		this.groupName = groupName;
		
		for(int i = 0; i < fieldNames.length; i++)
		{
			dbFields.put(fieldNames[i], dbNames[i]);
		}
		
		fieldsInView.add(fieldNames[0]);
		fieldsInView.add(fieldNames[1]);
	}
	
	/**
	 * Method that returns all of the local field names associeated with this table. 
	 * 
	 * @return the local field names associated with the table.
	 */
	public String[] getFields()
	{
		return this.fieldNames;
	}

	/**
	 * Method that returns the record type associated with the records in this table.
	 * 
	 * @return the record type of the record(s)
	 */
	public String getRecordType()
	{
		return this.recordType;
	}
	
	/**
	 * Method that returns the Options List Activities group name.
	 * 
	 * @return the group name for the options list Actiivty.
	 */
	public String getGroupName()
	{
		return this.groupName;
	}
	
	/**
	 * Method that returns the database field name associated with the local field name in this table.
	 * 
	 * @param fieldName a local field name.
	 * @return the database field name corresponding with the local field name
	 */
	public String getDBName(String fieldName)
	{
		return dbFields.get(fieldName);
	}
	
	/**
	 * Method that adds the passed in list of records to this table.
	 * 
	 * @param records list of records to be added to the table.
	 */
	public void addRecords(ArrayList<Record> records)
	{
		this.table_data = records;
		this.data = records.toArray(new Record[1]);
		this.tableSize = table_data.size();
	}
	
	/**
	 * Method that adds a new record to this table.
	 * 
	 * @param rec the new record to be added to the table.
	 */
	public void addRecord(Record rec)
	{
		this.table_data.add(rec);
		this.data = this.table_data.toArray(new Record[1]);
		this.tableSize = this.table_data.size();
	}
		
	/**
	 * Method that deletes all the record that are in this table.
	 */
	public void deleteRecords()
	{
		this.table_data.clear();			
	}
	
	/**
	 * Method that deletes a record with the corresponding ID.
	 * @param index the ID of the record to be deleted.
	 */
	public void deleteRecord(String index)
	{
		for(int i = 0; i < table_data.size(); i++)
		{
			if(this.table_data.get(i).getID().equalsIgnoreCase(index));
			{
				this.table_data.remove(i);
			}
		}
		this.data = table_data.toArray(new Record[1]);
	}
	
	/**
	 * Method that changes a record in the Table at a specific index.
	 * 
	 * @param index the ID of the record that is to be updated.
	 * @param values the values of the record that need to be changed.
	 */
	public void changeRecord(int index, String[] values)
	{
		this.table_data.get(index).fields = new Record(values).getFields();
		this.data = table_data.toArray(new Record[1]);
	}
	
	
	/**
	 * Method that adds a field to be viewed. 
	 * This is only done if there is not currently two fields in view.
	 * 
	 * @param field the field to be in view.
	 */
	public void addField(String field)
	{
		if(fieldsInView.size() < 2)
		{
			fieldsInView.add(field);
		}
	}
	
	/**
	 * Method that returns all of the current fields that the user is viewing.
	 * 
	 * @return the current fields in view to the user.
	 */
	public ArrayList<String> getFieldsInView()
	{
		return this.fieldsInView;
	}
	
	/**
	 * Method that returns all data associated with this table.
	 * 
	 * @return all of the data associated with the table.
	 */
	public ArrayList<Record> getTableData()
	{
		return this.table_data;
	}
	
	/**
	 * Accessor method that returns all of the records that are in this table.
	 * 
	 * @return all of the records that are in the table.
	 */
	public Record[] getRecords()
	{
		return this.data;
	}
	
	/**
	 * Method that returns the name associated with this table.
	 * 
	 * @return the current tables name associated with it.
	 */
	public String getTableName()
	{
		return this.table_name;
	}
	
	/**
	 * Mutator method to set the starting index of the record to be pulled in from the Database.
	 * 
	 * @param index the index to be set.
	 */
	public void setStartingIndex(int index)
	{
		this.startingIndex = index;
	}
	
	/**
	 * Method that returns the starting index of the records requested by the user.
	 * 
	 * @return the starting index of the records.
	 */
	public int getIndex()
	{
		return this.startingIndex;
	}
	
	/**
	 * Method that returns the number of records that are inside this table.
	 * 
	 * @return the size of the current table.
	 */
	public int tableSize()
	{
		return this.tableSize;
	}
	
}
