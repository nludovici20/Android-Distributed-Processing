package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.navigation.NavigationMain;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;

public class AuthenticateLoading extends Activity 
{
	private Intent engineIntent;
	private TextView loadingMessage;
	private Thread splashThread;
	public static boolean waiting = true;
	private String username, password, ipNum;
	private int port;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Authenticating with the Server...");
				
		
		this.username = getIntent().getExtras().getString("Username");
		this.password = getIntent().getExtras().getString("Password");
		this.ipNum = getIntent().getExtras().getString("IP");
		this.port = getIntent().getExtras().getInt("Port");
		
		// create a thread to show splash up to splash time
		splashThread = new Thread() {
		
			int wait = 0;
			@Override
			public void run() {
				try {
					super.run();
					
					Authenticate auth = new Authenticate(AuthenticateLoading.this, username, password, ipNum, port);
					
					//wait certain amount of time
					while (waiting) {
						sleep(100);
					}
				} catch (Exception e) {
					//handle
				} finally {
					//after splash screen, return to activity
					finish();
				}
			}
		};
		splashThread.start();
	
	}
}