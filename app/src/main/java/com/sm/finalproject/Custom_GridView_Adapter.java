package com.sm.finalproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_GridView_Adapter extends BaseAdapter {
    String[] context;
    ArrayList<String> ALName = new ArrayList<>();
    int flags[];
    LayoutInflater inflater;
    public Custom_GridView_Adapter(Context applicationContext, ArrayList<String> Name, int[] flags) {
        this.context = context;
        this.ALName = Name;
        this.flags = flags;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {return ALName .size();}
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.row_gridview, null);
        TextView country = (TextView) view.findViewById(R.id.idTVCourse);
        ImageView icon = (ImageView) view.findViewById(R.id.idIVcourse);
        country.setText(ALName.get(position));
        icon.setImageResource(flags[position]);
        return view;
    }
}
