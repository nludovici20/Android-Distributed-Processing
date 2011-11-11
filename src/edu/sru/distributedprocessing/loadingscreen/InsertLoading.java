package edu.sru.distributedprocessing.loadingscreen;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.Main;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InsertLoading extends Activity {
	String tableName, intent; 
	String[] record;
	Intent engineIntent = null;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		TextView loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Sending Data...");
		
		
		//get passed in tablename
		tableName = getIntent().getExtras().getString("TableName");
		record = getIntent().getExtras().getStringArray("Record");
		intent = getIntent().getExtras().getString("Intent");
		
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
					
					if(intent.equalsIgnoreCase("edit"))
					{
						Initialize.tcp.sendChangeRequest(tableName, record);	
					}else
						if(intent.equalsIgnoreCase("insert"))
						{
							Initialize.tcp.sendInsertRequest(tableName, record);
						}
					
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