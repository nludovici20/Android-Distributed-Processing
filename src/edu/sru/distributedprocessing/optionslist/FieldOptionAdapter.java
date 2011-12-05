package edu.sru.distributedprocessing.optionslist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import edu.sru.distributedprocessing.R;

/**
 * @author Gustavo Matias
 * url: http://mylifewithandroid.blogspot.com/2010/02/expandable-lists-and-check-boxes.html
 */
public class FieldOptionAdapter extends BaseExpandableListAdapter 
{
    private ArrayList<String> groups; //optionList groups
    private ArrayList<ArrayList<FieldOption>> fieldOption; //optionList fields
    private LayoutInflater inflater;

    public FieldOptionAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<FieldOption>> fieldOption ) 
    { 
        this.groups = groups;
        this.fieldOption = fieldOption;
        inflater = LayoutInflater.from( context );
    }

    //Inherited method - returns the child (field) name selected
    public Object getChild(int groupPosition, int childPosition) 
    {
        return fieldOption.get( groupPosition ).get( childPosition );
    }

    //Inherited method - returns the position of the child (field) selected
    public long getChildId(int groupPosition, int childPosition) 
    {
        return (long)( groupPosition*1024+childPosition );  // Max 1024 children per group
    }

    //get the entire view of the child (field) that was selected includeing Name, State, etc. 
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) 
    {
        View v = null;
        
        //check to to see if the view needs to be inflated with the fieldOptions
        if( convertView != null )
        {
            v = convertView; //use current view
        }else
        	{
        		v = inflater.inflate(R.layout.fields_row, parent, false); //inflate the view
        	}
        
        final FieldOption f = (FieldOption)getChild( groupPosition, childPosition ); //create a temp FieldOption to return
        
		TextView field_name = (TextView)v.findViewById( R.id.childname ); //child (field) name
		
		//populate the field_name with created FieldOption's field name
		if( field_name != null )
		{
			field_name.setText( f.getField());
		}
		
		final CheckBox cb = (CheckBox)v.findViewById( R.id.check_box );
		
        cb.setChecked( f.getState() ); //set appropriate state corresponding to fieldOption
        
        cb.setOnClickListener(new View.OnClickListener() 
        {
        	//@Override
        	public void onClick(View v) 
        	{
        		f.state = cb.isChecked();
        	}
        });
        
        return v; //return the view
    }

    //number of children (fields) in each Option
    public int getChildrenCount(int groupPosition) 
    {
        return fieldOption.get( groupPosition ).size();
    }

    //get the entire group in view
    public Object getGroup(int groupPosition) 
    {
        return groups.get( groupPosition );        
    }

    //get the number of groups (Options) in list
    public int getGroupCount() 
    {
        return groups.size();
    }

    //get the position of the group
    public long getGroupId(int groupPosition) 
    {
        return (long)( groupPosition*1024 );  // To be consistent with getChildId
    } 

    //get the enttire view of the group
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) 
    {
        View v = null;
        
        //check to see if we need to inflate the view
        if( convertView != null )
        {
            v = convertView; //use current
        }else
        	{
            	v = inflater.inflate(R.layout.group_row, parent, false); //inflate with FieldOption 
        	}
        
        String gt = (String)getGroup( groupPosition ); 
        
		TextView navigation_group = (TextView)v.findViewById( R.id.childname );
		
		if( gt != null )
		{
			navigation_group.setText( gt );
		}
		
		return v;
    }

    public boolean hasStableIds() 
    {
        return true;
    }
    
    public boolean isChildSelectable(int groupPosition, int childPosition) 
    {
        return true;
    } 
   

    public void onGroupCollapsed (int groupPosition) {} 
    public void onGroupExpanded(int groupPosition) {}


}
