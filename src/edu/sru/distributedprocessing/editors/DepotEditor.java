package edu.sru.distributedprocessing.editors;

import edu.sru.distributedprocessing.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DepotEditor extends Activity {
	String tableName;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_editor);
       
        tableName = "depots";
        
      
     
       
        }
        
       
}