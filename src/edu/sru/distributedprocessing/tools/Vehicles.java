package edu.sru.distributedprocessing.tools;

public class Vehicles {
	String plate_number, vin_number, vehicle_type, driver, depot, id, manufactured_year, available;
	
	public Vehicles(String id, String plate_num, String vin_num, String year, 
					String type, String driver, String depot, String avail)
	{
		this.id = id;
		this.plate_number = plate_num;
		this.vin_number = vin_num;
		this.manufactured_year = year; 
		this.vehicle_type = type;
		this.driver = driver;
		this.depot = depot;
		this.available = avail;
	}
	
	public void setID(String id)
	{
		this.id = id;
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public void setPlateNum(String plate_num)
	{
		this.plate_number = plate_num;
	}
	
	public String getPlateNum()
	{
		return this.plate_number;
	}
	
	public void setVinNum(String vin_num)
	{
		this.vin_number = vin_num;
	}
	
	public String getVinNum()
	{
		return this.vin_number;
	}
	
	public void setManufacturedYear(String year)
	{
		this.manufactured_year = year;
	}
	
	public String getManufacturedYear()
	{
		return this.manufactured_year;
	}
	
	public void setVehicleType(String type)
	{
		this.vehicle_type = type;
	}
	
	public String getVehicleType()
	{
		return this.vehicle_type;
	}
	
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	
	public String getDriver()
	{
		return this.driver;
	}
	
	public void setDepot(String depot)
	{
		this.depot = depot;
	}
	
	public String getDepot()
	{
		return this.depot;
	}
	
	public boolean isAvailable()
	{
		return true;
		
	}
	
}
