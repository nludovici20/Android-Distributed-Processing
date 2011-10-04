package edu.sru.distributedprocessing;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private OnClickListener mListener;

    public GridAdapter(Context c, OnClickListener o) {
        mContext = c;
        mListener = o;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageButton for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton buttonView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            buttonView = new ImageButton(mContext);
            buttonView.setLayoutParams(new GridView.LayoutParams(150, 150));
            buttonView.setBackgroundResource(R.layout.button_component1);
        } else {
            buttonView = (ImageButton) convertView;
        }
        buttonView.setImageResource(mThumbIds[position]);
        buttonView.setOnClickListener(mListener);
        buttonView.setTag(position);
        return buttonView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.vehicle, R.drawable.contact,
            R.drawable.icon, R.drawable.vehicle_types,
            R.drawable.warehouse, R.drawable.icon
    };
}