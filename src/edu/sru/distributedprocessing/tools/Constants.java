package edu.sru.distributedprocessing.tools;

import java.util.Hashtable;

import edu.sru.distributedprocessing.tableobjects.Table;

public class Constants 
{
	/*
	 * Keeps track of what fields the user wants to view from Options Menu
	 */
	public static Hashtable<String, String> vehicle_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> driver_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> shipment_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> routing_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> contractor_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> depot_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> warehouse_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> vehicle_type_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> maintenance_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> technician_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> contact_fields = new Hashtable<String, String>();
	public static Hashtable<String, String> report_fields = new Hashtable<String, String>();
	
	/*
	 * Keeps track of the Tables containing records
	 */
	public static Table vehicle_table = new Table("Vehicle Table");
	public static Table driver_table = new Table("Driver Table");
	public static Table shipment_table = new Table("Shipment Table");
	public static Table routing_table = new Table("Routing Table");
	public static Table contractor_table = new Table("Contractor Table");
	public static Table depot_table = new Table("Depot Table");
	public static Table warehouse_table = new Table("Warehouse Table");
	public static Table vehicle_type_table = new Table("Vehicle Type Table");
	public static Table maintenance_table = new Table("Maintenance Table");
	public static Table technician_table = new Table("Technician Table");
	public static Table contact_table = new Table("Contact Table");
	public static Table report_table = new Table("Report Table");
}
