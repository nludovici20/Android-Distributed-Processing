package edu.sru.distributedprocessing.database;
import java.io.IOException;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class DbManager extends SQLiteOpenHelper {
	
	private static final String DB_PATH = "/data/data/def.android.database/";
	private static final String ASSETS_PATH = "database/";
	private static final String DB_NAME = "android.db";
	private static final String FULL_PATH = DB_PATH+DB_NAME;
	private static final int DB_VERSION = 1;
	
	
	//Driver table
	static final String dbName="DB";
	static final String DriversTable="Drivers";
	static final String _dID="DriverID";
	static final String dLastName="DriverLastName";
	static final String dFirstName="DriverFirstName";
	static final String dLicNum="LicenseNumber";
	static final String dLicEx="LincenseExpiration";
	static final String dLicCl="LincenseClass";

	//Vehicles Types Table
	static final String VehiclesTypeTable="VehiclesType";
	static final String _VTId="VehiclesTypeId";
	static final String VTtype="Type";
	static final String VTStype="SubType";
	static final String VTModel="Model";
	static final String VTMaxW="MaxWeight";
	static final String VTMaxR="MaxRange";
	static final String VTLen="Length";

	//Vehicle table
	static final String VehiclesTable="Vehicles";
	static final String _VId="VehicleId";
	static final String VPlate="PlateNumber";
	static final String VVin="VinNumber";
	static final String VManu="ManufacturedYear";
	static final String VType="VehicleType";
	static final String VDriver="Driver";
	static final String VDepot="Depot";
	static final String VAvai="Available";

	//Depot table
	static final String DepotTable="Depot";
	static final String _DeId="DepotId";
	static final String DeName="Name";
	static final String DeAddress="Address";
	static final String DeCity="City";
	static final String Destate="State";
	static final String DeZip="ZipCode";
	static final String DeLat="Latitude";
	static final String DeLong="Longitude";

	//Contact table
	static final String ContactTable="Contact";
	static final String _CId="ContactId";
	static final String CLastName="LastName";
	static final String CFirstName="FirstName";
	static final String CMiddle="MiddleInitial";
	static final String CPriPhone="PrimaryPhone";
	static final String CWorkPhone="WorkPhone";

	//maintenance table
	static final String MaintTable="maintenance";
	static final String _MId="MaintId";
	static final String MReq="RequestedBy";
	static final String MTech="Technician";
	static final String MSch="ScheduledDate";
	static final String Mdet="Details";
	static final String MCost="Cost";
	static final String MVPlate="VehiclePlateNo";

	
	private SQLiteDatabase db;
	private Context context;
	
	public DbManager(Context context) 
	{
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}
	
	

	public void createDB() throws IOException
	{
		boolean dbExists = checkDB();
		if(dbExists)
		{
			System.out.println("DB Exists!");
			
		}
		else
		{	
			db.execSQL("CREATE TABLE "+DriversTable+" ("+_dID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+dFirstName+" TEXT, "+dLastName+" TEXT, "+
			        dLicNum+" TEXT, "+dLicEx+" TEXT, "+dLicCl+" TEXT, ");
			db.execSQL("CREATE TABLE "+VehiclesTypeTable+" ("+_VTId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				VTtype+" TEXT, "+VTStype+" TEXT, "+VTModel+" TEXT, "+VTMaxR+" TEXT, "+VTMaxW+" TEXT, "+VTLen+" Text, ");
			db.execSQL("CREATE TABLE "+VehiclesTable+" ("+_VId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					VPlate+" TEXT, "+VVin+" TEXT, "+VManu+" TEXT, "+VType+" TEXT, "+VDriver+" TEXT, "+VDepot+" Text, "+VAvai+"Text ");
			db.execSQL("CREATE TABLE "+DepotTable+" ("+_DeId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					DeName+" TEXT, "+DeAddress+" TEXT, "+DeCity+" TEXT, "+Destate+" TEXT, "+DeZip+" TEXT, "+DeLat+" Text, "+DeLong+" text");
			db.execSQL("CREATE TABLE "+ContactTable+" ("+_CId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					CLastName+" TEXT, "+CFirstName+" TEXT, "+CMiddle+" TEXT, "+CPriPhone+" TEXT, "+CWorkPhone+" TEXT, ");
			db.execSQL("CREATE TABLE "+MaintTable+" ("+_MId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					MReq+" TEXT, "+MTech+" TEXT, "+MSch+" TEXT, "+Mdet+" TEXT, "+MCost+" TEXT, "+MVPlate+" Text, ");	
		}
	}
	

	private boolean checkDB()
	{
		SQLiteDatabase sqldb = null;
			
		/*
		 * try to open the database. If it doesn't exist throw an exception and return false
		 * If database exists, return true.
		 */
		try
		{
			
			sqldb = SQLiteDatabase.openDatabase(FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch(SQLiteException e)
		{
			Log.w("Warning", "Database not found");
		}
		
		if(sqldb != null)
		{
			sqldb.close();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void openDB() throws SQLException
	{
		db = SQLiteDatabase.openDatabase(FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	public void insertValue(String tablename,String[] arraydata,int size)
	{	
		String Query= "Insert into"+tablename +" values (";
		int i=0;
		while(size>i)
		{
			
			Query+=" "+arraydata[i];
			if(i+1<size)
			{
				Query+=",";
			}
			
			i++;
		}
		Query+=")";
		db.execSQL(Query);
	}
	
	public void updateValue(String tablename,String id,String newValue,String Column1,String idvalue)
	{
		String Query= "update "+tablename +" set "+Column1+"="+newValue+" where "+id+"="+idvalue;
		db.execSQL(Query);	
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase arg0)
	{
		try {
			createDB();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}



	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

}
