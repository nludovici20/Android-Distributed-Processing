/*
 * Authentication Dialog that allows the user to input a:
 * Username
 * Password;
 * IP 
 * Port
 * to Authenticate with the server
 */

package edu.sru.distributedprocessing.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.loadingscreen.AuthenticateLoading;
import edu.sru.distributedprocessing.net.Authenticate;

public class AuthenticationDialog extends Dialog 
{
    private Activity activity;	//current activity
    private EditText username, password, ipNum, portNum; //attribute input boxes
    private Button authenticate_btn;
    private String user, pword, ip, port; //local config file attributes
    
    /*
     * Basic Constructor for first time Authentication
     * inputs: Activity, Dialog Theme
     */
    public AuthenticationDialog(Activity act, int theme) 
    {
        super(act, theme); //Dialog
        this.activity = act;
    }
    
    /*
     * Constructor Used to send in attributes from the Configuration File
     * inputs: Activity, Dialog Theme, Username, Password, IP, Port Number
     */
    public AuthenticationDialog(Activity act, int theme, String username, String password, String ip, String port)
    {
    	super(act, theme);
    	this.activity = act;
    	this.user = username;
    	this.pword = password;
    	this.ip = ip;
    	this.port = port;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);
        
        //Find config attribute blocks in layout file
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.userPassword);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        ipNum = (EditText)findViewById(R.id.ipNum);
        portNum = (EditText)findViewById(R.id.portNum);
        
        /*
         * Try setting the attributes passed in
         * (Only if using 2nd constructor)
         */
        try{
        	username.setText(user);
        	password.setText(pword);
        	ipNum.setText(ip);
        	portNum.setText(port);
        }catch(Exception e)
        {
        	e.printStackTrace();
        	//Instantiated using Basic Constructor
        }
        
        /*
         * Button that tries to Authenticate with the server with
         * the passed in Attributes
         */
        authenticate_btn = (Button) findViewById(R.id.authenticate_btn);
        authenticate_btn.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{
					//Loading class that while Authenticating
					Intent i = new Intent(activity, AuthenticateLoading.class);
					
					/**** Passed in Attributes ****/
					i.putExtra("Username", username.getText().toString());
					i.putExtra("Password", password.getText().toString());
					i.putExtra("IP", ipNum.getText().toString());
					i.putExtra("Port", Integer.parseInt(portNum.getText().toString()));
					/**** End Passed in Attributes ****/
					
					activity.startActivity(i); //start activity
					
				}catch(Exception e)
				{
					e.printStackTrace();
					
					//If all attributes were not filled in, Alert user
					Toast.makeText(activity, "All Fields are Required to Authenticate", Toast.LENGTH_SHORT).show();
					activity.finish(); //finish activity
				}
				dismissCustomDialog();
			}
        });
        
    }
    
    //close the dialog
    private void dismissCustomDialog()
    {
    	this.dismiss(); 
    }
    
    /*
     * If physical back button is pressed,
     * Close the dialog, and finish the activity
     */
    @Override
    public void onBackPressed()
    {
    	dismissCustomDialog();
    	activity.finish();
    }
    
   

}