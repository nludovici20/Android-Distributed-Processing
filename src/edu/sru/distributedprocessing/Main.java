package edu.sru.distributedprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import edu.sru.distributedprocessing.net.Authenticate;

public class Main extends Activity 
{
	private Initialize init;
	private Intent engineIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
          
        init = new Initialize(this);
        
        Button start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				engineIntent = new Intent(Main.this, NavigationMain.class);
				startActivity(engineIntent);
			}
		});
        
        Button options_btn = (Button) findViewById(R.id.options_btn);
        options_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				engineIntent = new Intent(Main.this, Settings.class);
				startActivity(engineIntent);
			}
		});
        
        Button about_btn = (Button) findViewById(R.id.about_btn);
        about_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				engineIntent = new Intent(Main.this, About.class);
				startActivity(engineIntent);
			}
		});
        
        Button quit_btn = (Button) findViewById(R.id.quit_btn);
        quit_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				try {
					Authenticate.tcp.finish();
				} catch (Exception e) {
					Log.v("ADP", "Main.class - Error finishing tcp thread");
				}
				finish(); //finish current activity
			}
		});        
        
    }		
}