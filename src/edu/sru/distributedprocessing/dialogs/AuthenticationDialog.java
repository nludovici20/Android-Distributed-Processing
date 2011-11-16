package edu.sru.distributedprocessing.dialogs;

import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.net.Authenticate;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticationDialog extends Dialog 
{
    Activity activity;	//current activity
    Authenticate auth;
    
    public AuthenticationDialog(Activity act, int theme) 
    {
        super(act, theme);
        this.activity = act;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);
        
        final EditText username = (EditText)findViewById(R.id.userName);
        final EditText password = (EditText)findViewById(R.id.userPassword);
        final EditText ipNum = (EditText)findViewById(R.id.ipNum);
        final EditText portNum = (EditText)findViewById(R.id.portNum);
        
        Button authenticate_btn = (Button) findViewById(R.id.authenticate_btn);
        authenticate_btn.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				auth = new Authenticate(activity, username.getText().toString(), password.getText().toString(), ipNum.getText().toString(), Integer.parseInt(portNum.getText().toString()));
				dismissCustomDialog();
			}
        });
        
    }
    
    //close the dialog
    private void dismissCustomDialog()
    {
    	this.dismiss();
    }
    
   

}