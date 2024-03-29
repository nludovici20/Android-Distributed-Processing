package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tools.Constants;

/**
 * The RecordLoading class is used to show the user that the client is communicating with the server.
 * 
 * @author Nick Ludovici
 */
public class RecordLoading extends Activity {
	private String tableName; /** name of the table currently in view **/
	private int recordIndex; /** ID of the record being edited **/
	private TextView loadingMessage; /** message the user will see while loading information **/
	private Intent engineIntent = null;
	private Thread splashThread; /** thread used to show the loading screen **/
	public static boolean waiting = true;
	
	/**
	 * This method is called on the Activities creation.
	 * The loading message is set, and the client attempts to pull in a record from the database. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		tableName = getIntent().getExtras().getString("Name");
		recordIndex = getIntent().getExtras().getInt("Index");
		
		loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Pulling Record From Database...");
		
		//Get correct Editor to pull data into
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
						if (tableName.equalsIgnoreCase("vehicletype"))
						{
							engineIntent = new Intent(this, VehicleTypeEditor.class);
						}else
							if (tableName.equalsIgnoreCase("vehicles"))
							{
								engineIntent = new Intent(this, VehicleEditor.class);
							}
		}
		
		/*** Prepare Data to send to Editor ***/
		engineIntent.putExtra("Intent", "edit");
		engineIntent.putExtra("Fields", Constants.db.getTable(tableName).getFields());
		engineIntent.putExtra("Index", getIntent().getExtras().getInt("ListIndex"));
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
			@Override
			public void run() {
				try {
					super.run();
					
					//send the record request to be pulled in
					Authenticate.tcp.sendRecordRequest(tableName, recordIndex);					
					
					//wait certain amount of time
					while (waiting) {
						sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("ADP", "RecordLoading - Exception");
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