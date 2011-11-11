package edu.sru.distributedprocessing.optionslist;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.sru.distributedprocessing.Initialize;
import edu.sru.distributedprocessing.IntelliSyncActivity;
import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.R.id;
import edu.sru.distributedprocessing.R.layout;
import edu.sru.distributedprocessing.loadingscreen.TableLoading;
import edu.sru.distributedprocessing.shippingscreen.ShippingScreen;
import edu.sru.distributedprocessing.tableobjects.Table;
import edu.sru.distributedprocessing.tools.Constants;
import android.util.Log;

public class Options extends ExpandableListActivity
{
	private ArrayList<String> groupNames;
	private ArrayList<ArrayList<FieldOption>> field_options;
	private ArrayList<FieldOption> fields;
	private FieldOptionAdapter expListAdapter;
	private EditText index;
	private String type;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.options_main);
	    
	    
	    type = getIntent().getExtras().getString("Type"); //type of records
	    index = (EditText) findViewById(R.id.starting_index); //starting index of list
	    
	    //add the group names (navigation panel elements)
	    groupNames = new ArrayList<String>();
	   	    
	    //child options in the groupNames (field elements)
	    field_options = new ArrayList<ArrayList<FieldOption>>(); 
	   
	    /*
	     * loop through tables
	     * if passed in type = tables records type, set index from table
	     */
		for(int i = 0; i < Constants.db.getTables().length; i++)
		{
			try
			{
				if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
				{
					index.setText(""+Constants.db.getTables()[i].getIndex());
					
					groupNames.add( Constants.db.getTables()[i].getTableName());
					
					fields = new ArrayList<FieldOption>();
		    		Log.v("ADP", "Creating OptionsList for table: " + Constants.db.getTables()[i].getTableName());
		    		for(int j = 0; j < Constants.db.getTables()[i].getFields().length; j++)
		    		{
		    			fields.add(new FieldOption( Constants.db.getTables()[i].getFields()[j], false) );
		    			Log.v("ADP", "Field Option: " + Constants.db.getTables()[i].getFields()[j]);
		    		}
		    		field_options.add(fields);
				}
			}catch (Exception e)
			{
				Log.v("ADP", "Options.class - Error setting index");
			}
			Log.v("ADP", "" + "Options.class - " + Constants.db.getTables()[i].getIndex());
		}
			    			
			expListAdapter = new FieldOptionAdapter( this,groupNames, field_options );
			setListAdapter( expListAdapter );
			
			/**							**/
			Button clear_all = (Button) findViewById(R.id.clear_btn);
			clear_all.setOnClickListener(new View.OnClickListener()
			{
				//@Override
				public void onClick(View v) 
				{
					//clear all fields in view
					for(int i = 0; i < Constants.db.getTables().length; i++)
					{
						Constants.db.getTables()[i].getFieldsInView().clear();
					}
				}
			});
	}
	
	public void  onContentChanged  () 
    {
        super.onContentChanged();
    }

    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int fieldPosition, long id) 
    {
		CheckBox cb = (CheckBox) v.findViewById(R.id.check_box);
		final FieldOption f = (FieldOption)expListAdapter.getChild(groupPosition, fieldPosition);
		
			//check if childs check box is selected or not
			if(cb.isChecked())
			{
				cb.toggle(); //if already checked, and clicked toggle state
				f.state = !cb.isChecked(); //set new state
				
				//loop through tables
				for(int i = 0; i < Constants.db.getTables().length; i++)
				{
					try
					{
						//if groupName is same as tables groupName
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase(Constants.db.getTables()[i].getGroupName()))
						{
								Constants.db.getTables()[i].getFieldsInView().remove(f.getField()); //remove the field unchecked from fieldsInView
						}
					}catch(Exception e)
					 {
						Log.v("ADP", "Options.class - Error removing child (field) from fieldsInView");
					 }	
				}	
				Log.v("Distributed-Processing", groupNames.get(groupPosition).toString() + "-" + f.getField() + ": Unchecked");
			}else{
				cb.toggle(); //if not checked, toggle state
				f.state = cb.isChecked(); //set new state
				
				//loop through tables
				for(int i = 0; i < Constants.db.getTables().length; i++)
				{
					try
					{
						//if groupName selected is same as tables groupName
						if(groupNames.get(groupPosition).toString().equalsIgnoreCase(Constants.db.getTables()[i].getGroupName()))
						{
							//if able to add a new fieldInView
							if (Constants.db.getTables()[i].getFieldsInView().size() < 2)
							{
								Constants.db.getTables()[i].addField(f.getField()); //add new field
							}else
							{
								//alert user that fieldsInView are at max capacity
								Toast.makeText(this, "Two fields already selected, clear fields to add more", Toast.LENGTH_SHORT).show();
								cb.toggle(); //toggle child's checkbox
							}
						}
					}catch(Exception e)
					{
						Log.v("ADP", "Options.class - Error adding new fieldInview");
					}
				}
				Log.v("ADP", "Options.class - " + groupNames.get(groupPosition).toString() + "-" + f.getField() + ": Checked");
			}		
    	return false;
    }

    public void  onGroupExpand  (int groupPosition) 
    {
    	//which group was expanded?
    	Log.v("ADP", "Options.class - " + groupNames.get(groupPosition).toString());
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed()
    {
    	finish(); //finish activity
    	Intent engineIntent = new Intent(Options.this, TableLoading.class); //create a new intent
    	//loop through tables
    	for(int i = 0; i < Constants.db.getTables().length; i++)
		{
    		try
    		{
    			//if type of table passed in = tables recordType
				if(type.equalsIgnoreCase(Constants.db.getTables()[i].getRecordType()))
				{
					//set appropriate starting index
					Constants.db.getTables()[i].setStartingIndex(Integer.parseInt(index.getText().toString()));
					engineIntent.putExtra("TableName", Constants.db.getTables()[i].getTableName());
				}
    		}catch (Exception e)
    		{
    			Log.v("ADP", "Options.class - Error sending request to server");
    		}
		}
 
    	Constants.db.saveDB(this); //save db attributes to file    	
    	startActivity(engineIntent); //start the activity
    	Log.v("ADP", "Options.class - " + type);
    	
    }
}