/*
 * Loading Screen while pulling in an entire table
 */
package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	public static boolean waiting = true;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		//get passed in tablename
		tableName = getIntent().getExtras().getString("TableName");
		
		engineIntent = new Intent(this, IntelliSyncActivity.class);
		
		tbl = Constants.db.getTable(tableName);
		engineIntent.putExtra("Type", tbl.getRecordType());
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
			@Override
			public void run() {
				try {
					super.run();
					
					//send table request
					Authenticate.tcp.sendTableRequest(tbl);
					
					//wait certain amount of time
					while (waiting) {
						sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("ADP", "TableLoading - Exception");
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