/*
 * Class that displays our About page.
 */

package edu.sru.distributedprocessing;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.sru.distributedprocessing.tools.FileManager;

public class Options extends Activity 
{
	private EditText username, password, ipNum, portNum;
	private Button authenticate_btn;
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
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
				String[] info = { username.getText().toString(), password.getText().toString(), ipNum.getText().toString(), portNum.getText().toString() };
				try
				{
					FileManager.saveConfigFile(Options.this, info, "Config");
					Log.v("ADP", "Options.class - Successfully saved Config file");
				}catch(Exception e)
				{
					Log.v("ADP", "Options.class - Error Saving Config file");
				}
			}
        });
        
	}

}
