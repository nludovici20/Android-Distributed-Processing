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
		this.available = new Field("Available?", avail, false);
	}
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
	public void setID(String id)
	{
		this.id.setValue(id);
	}
	
	public String getID()
	{
		return this.id.getValue();
	}
	
	public void setPlateNum(String plate_num)
	{
		this.plate_number.setValue(plate_num);
	}
	
	public String getPlateNum()
	{
		return this.plate_number.getValue();
	}
	
	public void setVinNum(String vin_num)
	{
		this.vin_number.setValue(vin_num);
	}
	
	public String getVinNum()
	{
		return this.vin_number.getValue();
	}
	
	public void setManufacturedYear(String year)
	{
		this.manufactured_year.setValue(year);
	}
	
	public String getManufacturedYear()
	{
		return this.manufactured_year.getValue();
	}
	
	public void setVehicleType(String type)
	{
		this.vehicle_type.setValue(type);
	}
	
	public String getVehicleType()
	{
		return this.vehicle_type.getValue();
	}
	
	public void setDriver(String driver)
	{
		this.driver.setValue(driver);
	}
	
	public String getDriver()
	{
		return this.driver.getValue();
	}
	
	public void setDepot(String depot)
	{
		this.depot.setValue(depot);
	}
	
	public String getDepot()
	{
		return this.depot.getValue();
	}
	
	public boolean isAvailable()
	{
		if(this.available.getAvailability() == "true")
		{
			return true;
		}
		 else
		 {
			return false;
		 }
	}	
}
