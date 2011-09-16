package edu.sru.distributedprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent engineIntent = new Intent(Main.this, NavigationMain.class);
				startActivity(engineIntent);
			}
		});
        
        Button options_btn = (Button) findViewById(R.id.options_btn);
        options_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent engineIntent = new Intent(Main.this, Options.class);
				startActivity(engineIntent);
			}
		});
        
        Button about_btn = (Button) findViewById(R.id.about_btn);
        about_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(Main.this, "Not Available Yet", Toast.LENGTH_LONG).show();
			}
		});
        
        Button quit_btn = (Button) findViewById(R.id.quit_btn);
        quit_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});        
        
    }
}