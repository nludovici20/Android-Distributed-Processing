package edu.sru.distributedprocessing.net;

import android.app.Activity;
import android.util.Log;
import edu.sru.distributedprocessing.tools.FileManager;

/**
 * This class handles the creation of the TCP client thread, 
 * as well as its authentication with the server.
 * 
 * @author Nick Ludovici
 */
public class Authenticate {
	public static TCPClient tcp;
	
	/**
	 * This constructor recieve's the users information, as well as the machine and port where the server program resides.
	 * 
	 * @param act the current activity trying to authenticate with the server.
	 * @param username the current users username for logging on to the server.
	 * @param password the current users password mapped to the username for authenticating with the server.
	 * @param ip the IP of the machine where the server program is located.
	 * @param port the port on which the server program is listening for incoming client requests.
	 */
	public Authenticate(Activity act, String username, String password, String ip, int port)
	{
		Log.v("ADP", username + " " + password + " " + ip + "  " + port);
		String[] info = { username, password, ip, ""+ port };
		//initialize a new Client and connect with server
		try
		{
			FileManager.username = username;
			FileManager.password = password;
			tcp = new TCPClient(act, ip, port); //connect to server
			tcp.start(); //start the thread
			tcp.send(Message.Type.AUTHENTICATE + username + Message.Type.AUTHENTICATE + password + Message.Type.AUTHENTICATE); //send initial message to server
			FileManager.saveConfigFile(act, info, "Config");
		}catch (Exception e)
		{
			e.printStackTrace();
			Log.d("ADP", "ERROR CONNECTING");
			act.finish();
		}
	}
}
