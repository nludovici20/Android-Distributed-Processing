package edu.sru.distributedprocessing.tableobjects;

public class VehicleRecord extends Record {
	private Field plate_number, vin_number, vehicle_type, driver, depot, id, manufactured_year, available;
	private String[] field_names;
	private Field[] myFields;
	private String record_type;
	
	public VehicleRecord(String id, String plate_num, String vin_num, String year, 
					String type, String driver, String depot, boolean avail)
	{
		this.record_type = "Vehicle";
		this.id = new Field("ID", id, false);
		this.plate_number = new Field("License Plate Number", plate_num, false);
		this.vin_number = new Field("Vin Number", vin_num, false);
		this.manufactured_year = new Field("Manufactured Year", year, false); 
		this.vehicle_type = new Field("Vehicle Type", type, false);
		this.driver = new Field("Driver", driver, false);
		this.depot = new Field("Depot", depot, false);
		if(avail == true)
		{
			this.available = new Field("Available?", "true", false);
		}else
			{
				this.available = new Field("Available?", "false", false);
			}
		myFields = new Field[] { this.id, this.plate_number, this.vin_number, this.manufactured_year, this.vehicle_type, this.driver, this.depot, this.available};
	}
	
	@Override
	public Field getField(String fieldName)
	{
		if(id.getFieldName().equalsIgnoreCase(fieldName))
		{
			return id;
		}else 
			if(plate_number.getFieldName().equalsIgnoreCase(fieldName))
			{
				return plate_number;
			}else
				if(vin_number.getFieldName().equalsIgnoreCase(fieldName))
				{
					return vin_number;
				}else
					if(manufactured_year.getFieldName().equalsIgnoreCase(fieldName))
					{
						return manufactured_year;
					}else
						if(vehicle_type.getFieldName().equalsIgnoreCase(fieldName))
						{
							return vehicle_type;
						}else
							if(driver.getFieldName().equalsIgnoreCase(fieldName))
							{
								return driver;
							}else
								if(depot.getFieldName().equalsIgnoreCase(fieldName))
								{
									return depot;
								}else
									if(available.getFieldName().equalsIgnoreCase(fieldName))
									{
										return available;
									}
		return id;
			
	}
	
	@Override
	public void setFieldValue(String fieldname, String value)
	{
		getField(fieldname).setValue(value);
	}

	@Override
	public String[] getFields() {
		
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
