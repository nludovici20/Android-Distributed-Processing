package edu.sru.distributedprocessing.tableobjects;

public class DriverRecord extends Record {
	private Field id, last_name, first_name, vehicle_plate, license_num, license_expiration, license_class;
	private String[] field_names;
	private Field[] myFields;
	private String record_type;
	
	public DriverRecord(String id, String lastName, String firstName, String plateNum, String licenseNum, String licenseExp, String licenseClass)
	{
		this.record_type = "Driver";
		this.id = new Field("ID", id, false);
		this.last_name = new Field("Last Name", lastName, false);
		this.first_name = new Field("First Name", firstName, false);
		this.vehicle_plate = new Field("License Plate Number", plateNum, false);
		this.license_num = new Field("License Number", licenseNum, false);
		this.license_expiration = new Field("License Expiration", licenseExp, false);
		this.license_class = new Field("License Class", licenseClass, false);
		
		myFields = new Field[] { this.id, this.last_name, this.first_name, this.vehicle_plate, this.license_num, this.license_expiration, this.license_class};
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
					if(vehicle_plate.getFieldName().equalsIgnoreCase(fieldName))
					{
						return vehicle_plate;
					}else
						if(license_expiration.getFieldName().equalsIgnoreCase(fieldName))
						{
							return license_expiration;
						}else
							if(license_class.getFieldName().equalsIgnoreCase(fieldName))
							{
								return license_class;
							}else
								if(license_num.getFieldName().equalsIgnoreCase(fieldName))
								{
									return license_num;
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
	
	
}
