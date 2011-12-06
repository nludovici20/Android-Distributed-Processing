package edu.sru.distributedprocessing.tableobjects;

/**
 * This class is used to represent a record from the database.
 * 
 * @author Nick Ludovici
 */
public class Record
{
	String id; /** the record ID assigned by the server's Database **/
	String[] fields; /** the field values assigned to the record **/
	
	/**
	 * This Constructor method sets the data passed in to the correct attributes of the record.
	 * 
	 * @param id the ID of the record assigned by the Database.
	 * @param field1 the value of the first field in view.
	 * @param field2 the value of the second field in view.
	 */
	public Record(String id, String field1, String field2)
	{
		this.id = id;
		this.fields[0] = field1;
		this.fields[1] = field2;
	}
	
	/**
	 * Constructor that sets the data passed in to the correct attributes of the record.
	 * 
	 * @param id the ID value of the record that is assigned by the Database.
	 * @param fields the values of the fields that are in view to a user.
	 */
	public Record(String id, String[] fields)
	{
		this.id = id;
		this.fields = fields;
	}
	
	/**
	 * Constructor that sets the fields of the record.
	 * 
	 * @param fields the values of the fields in view.
	 */
	public Record(String[] fields)
	{
		this.fields = fields;
	}
		
	
	/**
	 * Accessor method that returns all of the fields in the record.
	 * 
	 * @return all of the fields that correspond with this record.
	 */
	public String[] getFields()
	{
		return fields;
	}
	
	/**
	 * Accessor method that returns the ID of this record.
	 * 
	 * @return the ID associated with this record.
	 */
	public String getID()
	{
		return this.id;
	}	
}
