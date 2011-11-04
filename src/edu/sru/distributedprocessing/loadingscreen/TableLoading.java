package edu.sru.distributedprocessing.loadingscreen;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.Main;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContactEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TableLoading extends Activity {
	String tableName; 
	String tableType;
	Intent engineIntent = null;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		//get passed in tablename
		tableName = getIntent().getExtras().getString("TableName");
		
		engineIntent = new Intent(this, IntelliSyncActivity.class);
		
		final Table tbl = Constants.db.getTable(tableName);
		engineIntent.putExtra("Type", tbl.getRecordType());
		
		// set time to splash out
		final int splashDisplayTime = 3000;
		
		// create a thread to show splash up to splash time
		final Thread splashThread = new Thread() {
		
			int wait = 0;
			@Override
			public void run() {
				try {
					super.run();
					
					Initialize.tcp.sendTableRequest(tbl);
					
					//wait certain amount of time
					while (wait < splashDisplayTime) {
						sleep(100);
						wait += 100;
					}
				} catch (Exception e) {
					//handle
				} finally {
					//after splash screen, return to activity
					startActivity(engineIntent);
					finish();
				}
			}
		};
		splashThread.start();
	
	}
}