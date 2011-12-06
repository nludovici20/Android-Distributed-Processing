package edu.sru.distributedprocessing.loadingscreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.net.Authenticate;

/**
 * The AuthenticateLoading class is used to show the user that the client is communicating with the server.
 * 
 * @author Nick Ludovici
 */
public class AuthenticateLoading extends Activity 
{
	private TextView loadingMessage; /** message the user will see while loading information **/
	private Thread splashThread; /** thread used to show the loading screen **/
	public static boolean waiting = true;
	private String username, password, ipNum;
	private int port;
	
	/**
	 * This method is called on the Activities creation.
	 * The loading message is set, and the client attempts to authenticate with the server. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		loadingMessage = (TextView) findViewById(R.id.loadingMessage);
		loadingMessage.setText("Authenticating with the Server..."); //message user will see
		
		/* 
		 * Items passed in to Activity 
		 * Username, Password, IP, Port
		 */
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
					
					//authenticate with server
					Authenticate auth = new Authenticate(AuthenticateLoading.this, username, password, ipNum, port);
					
					//wait until server has authenticated or failed
					while (waiting) {
						sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.v("ADP", "AuthenticateLoading - Exception");
				} finally {
					//after splash screen, return to activity
					finish();
				}
			}
		};
		splashThread.start();
	
	}
}