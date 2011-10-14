package edu.sru.distributedprocessing.splashscreen;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.Main;
import edu.sru.distributedprocessing.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		
		// set time to splash out
		final int splashDisplayTime = 3000;
		
		// create a thread to show splash up to splash time
		final Thread splashThread = new Thread() {
		
			int wait = 0;
			
			@Override
			public void run() {
				try {
					super.run();
					
					//temp data created here
					Initialize init = new Initialize(SplashScreenActivity.this);
					
					//wait certain amount of time
					while (wait < splashDisplayTime) {
						sleep(100);
						wait += 100;
					}
				} catch (Exception e) {
					//handle
				} finally {
					//after splash screen, pull up main activity
					startActivity(new Intent(SplashScreenActivity.this, Main.class));
					finish();
				}
			}
		};
		splashThread.start();
	
	}
}