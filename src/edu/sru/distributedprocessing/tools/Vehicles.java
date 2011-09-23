package edu.sru.distributedprocessing.tools;

public class Vehicles extends Type {
	private Field plate_number, vin_number, vehicle_type, driver, depot, id, manufactured_year, available;
	
	public Vehicles(String id, String plate_num, String vin_num, String year, 
					String type, String driver, String depot, boolean avail)
	{
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
	
	
}
