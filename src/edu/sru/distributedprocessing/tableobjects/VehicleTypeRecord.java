package edu.sru.distributedprocessing.tableobjects;

public class VehicleTypeRecord extends Record {
	private Field id, vehicle_type, sub_type, model, max_weight, max_range, length;
	private String[] field_names;
	private Field[] myFields;
	private String record_type;
	
	public VehicleTypeRecord(String id, String vehicleType, String subType, String model, String maxWeight, String maxRange, String length)
	{
		this.record_type = "Vehicle Type";
		this.id = new Field("ID", id, false);
		this.vehicle_type = new Field("Vehicle Type", vehicleType, false);
		this.sub_type = new Field("Sub Type", subType, false);
		this.model = new Field("Model", model, false); 
		this.max_weight = new Field("", maxWeight, false);
		this.max_range = new Field("", maxRange, false);
		this.length = new Field("", length, false);
		
		myFields = new Field[] { this.id, this.vehicle_type, this.sub_type, this.model, this.max_weight, this.max_range, this.length};
	}
	
	@Override
	public Field getField(String fieldName)
	{
		if(id.getFieldName().equalsIgnoreCase(fieldName))
		{
			return id;
		}else 
			if(vehicle_type.getFieldName().equalsIgnoreCase(fieldName))
			{
				return vehicle_type;
			}else
				if(sub_type.getFieldName().equalsIgnoreCase(fieldName))
				{
					return sub_type;
				}else
					if(model.getFieldName().equalsIgnoreCase(fieldName))
					{
						return model;
					}else
						if(max_weight.getFieldName().equalsIgnoreCase(fieldName))
						{
							return max_weight;
						}else
							if(max_range.getFieldName().equalsIgnoreCase(fieldName))
							{
								return max_range;
							}else
								if(length.getFieldName().equalsIgnoreCase(fieldName))
								{
									return length;
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
	public String getRecordType() {
		return this.record_type;
	}
		
}
