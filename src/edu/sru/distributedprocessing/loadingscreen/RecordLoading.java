package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tools.Constants;

public class RecordLoading extends Activity {
	private String tableName; 
	private int recordIndex, splashDisplayTime;
	private Intent engineIntent = null;
	private Thread splashThread;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		tableName = getIntent().getExtras().getString("Name");
		recordIndex = getIntent().getExtras().getInt("Index");
		
		//Edit Record
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			if (tableName.equalsIgnoreCase("contractors"))
			{
				engineIntent = new Intent(this, ContractorEditor.class);
			}else
				if (tableName.equalsIgnoreCase("depots"))
				{
					engineIntent = new Intent(this, DepotEditor.class);
				}else
					if (tableName.equalsIgnoreCase("drivers"))
					{
						engineIntent = new Intent(this, DriverEditor.class);
					}else
						if (tableName.equalsIgnoreCase("vehicle type"))
						{
							engineIntent = new Intent(this, VehicleTypeEditor.class);
						}else
							if (tableName.equalsIgnoreCase("vehicles"))
							{
								engineIntent = new Intent(this, VehicleEditor.class);
							}
		}
		
		engineIntent.putExtra("Intent", "edit");
		engineIntent.putExtra("Fields", Constants.db.getTable(tableName).getFields());
		engineIntent.putExtra("Index", getIntent().getExtras().getInt("ListIndex"));
		
		// set time to splash out
		splashDisplayTime = 3000;
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
		
			int wait = 0;
			@Override
			public void run() {
				try {
					super.run();
					
					Authenticate.tcp.sendRecordRequest(tableName, recordIndex);					
					
					//wait certain amount of time
					while (wait < splashDisplayTime) {
						sleep(100);
						wait += 100;
					}
				} catch (Exception e) {
					//handle
				} finally {
					//after splash screen, return to activity
					finish();
					startActivity(engineIntent);
				}
			}
		};
		splashThread.start();
	
	}
}