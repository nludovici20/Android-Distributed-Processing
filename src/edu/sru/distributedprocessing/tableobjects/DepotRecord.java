package edu.sru.distributedprocessing.tableobjects;

public class DepotRecord extends Record {
	private Field id, depot_name, depot_address, city, state, zip_code, latitude, longitude;
	private String[] field_names;
	private Field[] myFields;
	private String record_type;
	
	public DepotRecord(String id, String name, String address, String city, String state, String zip, String latitude, String longitude)
	{
		this.record_type = "Depot";
		this.id = new Field("ID", id, false);
		this.depot_name = new Field("Depot Name", name, false);
		this.depot_address = new Field("Depot Address", address, false);
		this.city = new Field("City", city, false);
		this.state = new Field("State", state, false);
		this.zip_code = new Field("Zip Code", zip, false);
		this.latitude = new Field("Latitude", latitude, false); 
		this.longitude = new Field("Longitude", longitude, false);
		
		myFields = new Field[] { this.id, this.depot_name, this.depot_address, this.city, this.state, this.zip_code, this.latitude, this.longitude};
	}
	
	@Override
	public Field getField(String fieldName)
	{
		if(id.getFieldName().equalsIgnoreCase(fieldName))
		{
			return id;
		}else 
			if(depot_name.getFieldName().equalsIgnoreCase(fieldName))
			{
				return depot_name;
			}else
				if(depot_address.getFieldName().equalsIgnoreCase(fieldName))
				{
					return depot_address;
				}else
					if(latitude.getFieldName().equalsIgnoreCase(fieldName))
					{
						return latitude;
					}else
						if(city.getFieldName().equalsIgnoreCase(fieldName))
						{
							return city;
						}else
							if(state.getFieldName().equalsIgnoreCase(fieldName))
							{
								return state;
							}else
								if(zip_code.getFieldName().equalsIgnoreCase(fieldName))
								{
									return zip_code;
								}else
									if(longitude.getFieldName().equalsIgnoreCase(fieldName))
									{
										return longitude;
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
