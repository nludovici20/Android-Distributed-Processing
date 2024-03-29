package edu.sru.distributedprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import edu.sru.distributedprocessing.tools.FileManager;

/**
 * This class is used to display the about page located in the assets folder.
 * The about page contains information about the application, as well as those involved in creating it.
 * 
 * @author Nick Ludovici
 */
public class About extends Activity {
	/**
	 * Holds the text about the application.
	 */
	TextView about_text; //TextView holding the contents of the "About.xml" File
	
	/**
	 * Method that is called when the Activity is first created.
	 * Sets the text view to hold the data provided by the "about.xml" file.
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        
        about_text = (TextView)findViewById(R.id.about);
        
        /*
         * Read the Contents of the file "about.xml" using the 
         * Method from the FileManager class and set the TextView to 
         * contain those contents.
         */
        about_text.setText(FileManager.readXML(About.this, "about.xml", "text"));
	}
	
	

}
