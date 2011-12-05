/*
 * The Main Activity in the Application
 * 3 Buttons: Start Program, About, Log off
 */
package edu.sru.distributedprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import edu.sru.distributedprocessing.navigation.NavigationMain;
import edu.sru.distributedprocessing.net.Authenticate;
import edu.sru.distributedprocessing.net.TCPClient;
import edu.sru.distributedprocessing.tools.FileManager;

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
          
        init = new Initialize(this); //initialize the db and tables
       
        /***** Handle Button Clicks *****/
        Button start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				//Start the navigation menu activity
				 engineIntent = new Intent(Main.this, NavigationMain.class);
				 startActivity(engineIntent);
			}
		});
        
        Button about_btn = (Button) findViewById(R.id.about_btn);
        about_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				//Start About activity to display the about page
				engineIntent = new Intent(Main.this, About.class);
				startActivity(engineIntent);
			}
		});
        
        Button quit_btn = (Button) findViewById(R.id.quit_btn);
        quit_btn.setText("Log Off");
        quit_btn.setOnClickListener(new View.OnClickListener() 
        {
			//@Override
			public void onClick(View v) 
			{
				try {
					Authenticate.tcp.sendLogOffRequest();
					Authenticate.tcp.finish(); //finish the tcp thread
				} catch (Exception e) {
					Log.v("ADP", "Main.class - Error finishing tcp thread");
				}
				finish(); //finish current activity
			}
		}); 
        /***** End Handle Button Clicks *****/
        
    }		
}
