/*
 * Class that displays our About page.
 */

package edu.sru.distributedprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import edu.sru.distributedprocessing.tools.FileManager;

public class About extends Activity {
	TextView about_text;
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        
        about_text = (TextView)findViewById(R.id.about);
        about_text.setText(FileManager.readXML(About.this, "about.xml", "text"));
	}
	
	

}
