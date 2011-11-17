package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;

public class TableLoading extends Activity {
	private String tableName; 
	private Intent engineIntent;
	private Table tbl;
	private Thread splashThread;
	private int splashDisplayTime;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		//get passed in tablename
		tableName = getIntent().getExtras().getString("TableName");
		
		engineIntent = new Intent(this, IntelliSyncActivity.class);
		
		tbl = Constants.db.getTable(tableName);
		engineIntent.putExtra("Type", tbl.getRecordType());
		
		// set time to splash out
		splashDisplayTime = 3000;
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
		
			int wait = 0;
			@Override
			public void run() {
				try {
					super.run();
					
					Authenticate.tcp.sendTableRequest(tbl);
					
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