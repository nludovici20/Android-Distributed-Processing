package edu.sru.distributedprocessing.optionslist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import java.util.ArrayList;

import edu.sru.distributedprocessing.R;
import edu.sru.distributedprocessing.R.id;
import edu.sru.distributedprocessing.R.layout;

public class FieldOptionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> groups;
    private ArrayList<ArrayList<FieldOption>> fieldOption;
    private LayoutInflater inflater;

    public FieldOptionAdapter(Context context, 
                        ArrayList<String> groups,
						ArrayList<ArrayList<FieldOption>> fieldOption ) { 
        this.context = context;
		this.groups = groups;
        this.fieldOption = fieldOption;
        inflater = LayoutInflater.from( context );
    }

    public Object getChild(int groupPosition, int childPosition) {
        return fieldOption.get( groupPosition ).get( childPosition );
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long)( groupPosition*1024+childPosition );  // Max 1024 children per group
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = null;
        if( convertView != null )
            v = convertView;
        else
            v = inflater.inflate(R.layout.fields_row, parent, false); 
        final FieldOption f = (FieldOption)getChild( groupPosition, childPosition );
		TextView field_name = (TextView)v.findViewById( R.id.childname );
		if( field_name != null )
			field_name.setText( f.getField());
		final CheckBox cb = (CheckBox)v.findViewById( R.id.check_box );
        cb.setChecked( f.getState() );
        
        cb.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        	f.state = cb.isChecked();
        	}
        	});
        return v;
    }

    public int getChildrenCount(int groupPosition) {
        return fieldOption.get( groupPosition ).size();
    }

    public Object getGroup(int groupPosition) {
        return groups.get( groupPosition );        
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return (long)( groupPosition*1024 );  // To be consistent with getChildId
    } 

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = null;
        if( convertView != null )
            v = convertView;
        else
            v = inflater.inflate(R.layout.group_row, parent, false); 
        String gt = (String)getGroup( groupPosition );
		TextView navigation_group = (TextView)v.findViewById( R.id.childname );
		if( gt != null )
			navigation_group.setText( gt );
        return v;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    } 
   

    public void onGroupCollapsed (int groupPosition) {} 
    public void onGroupExpanded(int groupPosition) {}


}
