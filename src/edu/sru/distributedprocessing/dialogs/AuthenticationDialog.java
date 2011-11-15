package edu.sru.distributedprocessing.dialogs;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.editors.ContractorEditor;
import edu.sru.distributedprocessing.editors.DepotEditor;
import edu.sru.distributedprocessing.editors.DriverEditor;
import edu.sru.distributedprocessing.editors.VehicleEditor;
import edu.sru.distributedprocessing.editors.VehicleTypeEditor;
import edu.sru.distributedprocessing.loadingscreen.RecordLoading;
import edu.sru.distributedprocessing.tableobjects.Record;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AuthenticationDialog extends Dialog 
{
    Activity activity;	//current activity
      
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
				Initialize init = new Initialize(username.getText().toString(), password.getText().toString(), ipNum.getText().toString(), Integer.parseInt(portNum.getText().toString()), activity);
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