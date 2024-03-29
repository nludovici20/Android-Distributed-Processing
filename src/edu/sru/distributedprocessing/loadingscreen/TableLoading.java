package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
/**
 * The TableLoading class is used to show the user that the client is communicating with the server.
 * 
 * @author Nick Ludovici
 */
public class TableLoading extends Activity {
	private String tableName; /** name of the table currently in view **/
	private Intent engineIntent;
	private Table tbl; /** the table currently in view **/
	private TextView loadingMessage; /** message the user will see while loading information **/
	private Thread splashThread; /** thread used to show the loading screen **/
	public static boolean waiting = true;
	
	/**
	 * This method is called on the Activities creation.
	 * The loading message is set, and the client attempts to pull a table from the database. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		//get passed in tablename
		tableName = getIntent().getExtras().getString("TableName");
		engineIntent = new Intent(this, IntelliSyncActivity.class);
		
		loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Loading Table from Server...");
		
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