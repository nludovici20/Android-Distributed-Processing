package edu.sru.distributedprocessing.net;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import edu.sru.distributedprocessing.tools.FileManager;

public class Authenticate {
	public static TCPClient tcp;
	
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
