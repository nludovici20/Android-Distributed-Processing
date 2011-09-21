package edu.sru.distributedprocessing;

import edu.sru.distributedprocessing.tools.Vehicles;

public class Initialize {
	Vehicles v1,v2;
	public Initialize()
	{
		v1 = new Vehicles("1", "DEC-DFE1", "4B7DH3LDJNEE945D", "1982", "Flatbed", "McDonald, Mary", "West Depot", true);
		v2 = new Vehicles("2", "KD8-2GX", "56JFBNWUMNSJMWJ6", "1995", "Flatbed", "Niece, Sue","Garage A3", true);
	}
}
