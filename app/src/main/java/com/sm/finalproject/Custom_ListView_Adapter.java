package com.sm.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_ListView_Adapter extends BaseAdapter {
    String[] context;
    ArrayList<String> ALName;
    ArrayList<String> ALEmail;
    ArrayList<String> ALMod_no;
    int flags[];
    LayoutInflater inflater;
    public Custom_ListView_Adapter(Context applicationContext, ArrayList<String> Name, ArrayList<String> Email, ArrayList<String> Mob_no, int[] flags) {
        context = context;
        this.ALName = Name;
        this.ALEmail = Email;
        this.ALMod_no = Mob_no;
        this.flags = flags;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return ALName.size();
    }

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
        View view = inflater.inflate(R.layout.row_listview,null);
        TextView name = view.findViewById(R.id.Name);
        TextView email = view.findViewById(R.id.email);
        TextView mobilNo = view.findViewById(R.id.mobileNo);
        ImageView icon = view.findViewById(R.id.imgShow);
        name.setText(ALName.get(position));
        email.setText(ALEmail.get(position));
        mobilNo.setText(ALMod_no.get(position));
        icon.setImageResource(flags[position]);
        return view;
    }
}
