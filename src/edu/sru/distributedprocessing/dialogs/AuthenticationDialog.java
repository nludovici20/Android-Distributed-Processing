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
    private EditText username, password, ipNum, portNum; //config attributes
    private Button authenticate_btn;
    private String user, pword, ip, port;
    
    public AuthenticationDialog(Activity act, int theme) 
    {
        super(act, theme); //Dialog
        this.activity = act;
    }
    
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
        
        //config attributes
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.userPassword);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        ipNum = (EditText)findViewById(R.id.ipNum);
        portNum = (EditText)findViewById(R.id.portNum);
        
        try{
        	username.setText(user);
        	password.setText(pword);
        	ipNum.setText(ip);
        	portNum.setText(port);
        }catch(Exception e)
        {
        	//nothing
        }
        
        authenticate_btn = (Button) findViewById(R.id.authenticate_btn);
        authenticate_btn.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{
					Intent i = new Intent(activity, AuthenticateLoading.class);
					i.putExtra("Username", username.getText().toString());
					i.putExtra("Password", password.getText().toString());
					i.putExtra("IP", ipNum.getText().toString());
					i.putExtra("Port", Integer.parseInt(portNum.getText().toString()));
					activity.startActivity(i);
				}catch(Exception e)
				{
					Toast.makeText(activity, "All Fields are Required to Authenticate", Toast.LENGTH_SHORT).show();
					activity.finish();
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
    
    @Override
    public void onBackPressed()
    {
    	dismissCustomDialog();
    	activity.finish();
    }
    
   

}