package edu.sru.distributedprocessing.tableobjects;

public class ContactRecord extends Record {
	private Field id, last_name, first_name, middle_init, primary_phone, work_phone;
	private String[] field_names;
	private Field[] myFields;
	private String record_type;
	
	public ContactRecord(String id, String lastName, String firstName, String middleInitial, String primaryPhone, String workPhone)
	{
		this.record_type = "ContactType";
		this.id = new Field("ID", id, false);
		this.last_name = new Field("Last Name", lastName, false);
		this.first_name = new Field("First Name", firstName, false);
		this.middle_init = new Field("Middle Initial", middleInitial, false); 
		this.primary_phone = new Field("Primary Phone", primaryPhone, false);
		this.work_phone = new Field("Work Phone", workPhone, false);

		myFields = new Field[] { this.id, this.last_name, this.first_name, this.middle_init, this.primary_phone, this.work_phone };
	}
	
	@Override
	public Field getField(String fieldName)
	{
		if(id.getFieldName().equalsIgnoreCase(fieldName))
		{
			return id;
		}else 
			if(last_name.getFieldName().equalsIgnoreCase(fieldName))
			{
				return last_name;
			}else
				if(first_name.getFieldName().equalsIgnoreCase(fieldName))
				{
					return first_name;
				}else
					if(middle_init.getFieldName().equalsIgnoreCase(fieldName))
					{
						return middle_init;
					}else
						if(primary_phone.getFieldName().equalsIgnoreCase(fieldName))
						{
							return primary_phone;
						}else
							if(work_phone.getFieldName().equalsIgnoreCase(fieldName))
							{
								return work_phone;
							}
		return id;
			
	}
	
	@Override
	public void setFieldValue(String fieldname, String value)
	{
		getField(fieldname).setValue(value);
	}

	@Override
	public String[] getFields() 
	{
		
		field_names = new String[myFields.length];
		
		for(int i = 0; i < myFields.length; i++)
		{
			field_names[i] = myFields[i].getFieldName().toString();
		}
		return field_names;
	}

	@Override
	public String getRecordType() 
	{
		return this.record_type;
	}

	@Override
	public String getGroupName() {
		return "Contacts";
	}
	
	
}
