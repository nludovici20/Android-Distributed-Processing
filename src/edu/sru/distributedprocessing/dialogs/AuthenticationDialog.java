package edu.sru.distributedprocessing.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.net.Authenticate;

public class AuthenticationDialog extends Dialog 
{
    private Activity activity;	//current activity
    private EditText username, password, ipNum, portNum;
    private Button authenticate_btn;
    
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
        
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.userPassword);
        ipNum = (EditText)findViewById(R.id.ipNum);
        portNum = (EditText)findViewById(R.id.portNum);
        
        authenticate_btn = (Button) findViewById(R.id.authenticate_btn);
        authenticate_btn.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Authenticate auth = new Authenticate(activity, username.getText().toString(), password.getText().toString(), ipNum.getText().toString(), Integer.parseInt(portNum.getText().toString()));
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