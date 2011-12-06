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
 * The InsertLoading class is used to show the user that the client is communicating with the server.
 * 
 * @author Nick Ludovici
 */
public class InsertLoading extends Activity 
{
	private String tableName, intent; 
	private String[] record; /** record that is being edited **/
	private Intent engineIntent;
	private TextView loadingMessage; /** message the user will see while loading information **/
	private Table tbl; /** table currently in view **/
	private Thread splashThread; /** thread used to show the loading screen **/
	public static boolean waiting = true;
	
	/**
	 * This method is called on the Activities creation.
	 * The loading message is set, and the client attempts to insert a new record in the database. 
	 */	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Sending Data...");
				
		/*** Items passed in from previous activity ***/
		tableName = getIntent().getExtras().getString("TableName");
		record = getIntent().getExtras().getStringArray("Record");
		intent = getIntent().getExtras().getString("Intent");
		
		engineIntent = new Intent(this, IntelliSyncActivity.class);
		
		tbl = Constants.db.getTable(tableName);
		engineIntent.putExtra("Type", tbl.getRecordType());
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
		
			@Override
			public void run() {
				try {
					super.run();
					
					//Insert or Edit Request
					if(intent.equalsIgnoreCase("edit"))
					{
						Authenticate.tcp.sendChangeRequest(tableName, record);	
					}else
						if(intent.equalsIgnoreCase("insert"))
						{
							Authenticate.tcp.sendInsertRequest(tableName, record);
						}
					
					//wait certain amount of time
					while (waiting) {
						sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("ADP", "InsertLoading - Exception");
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