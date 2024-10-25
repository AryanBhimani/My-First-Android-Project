package com.sm.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class Custom_Recycler_Adapter extends RecyclerView.Adapter<Custom_Recycler_Adapter.ViewHolder> {
    ArrayList<String> ALname;
    ArrayList<String> ALEmail;
    ArrayList<String> ALmessage;

    public Custom_Recycler_Adapter(Context context, ArrayList<String> ALName, ArrayList<String> ALEmail, ArrayList<String> ALMessage) {
        this.ALname = ALName;
        this.ALEmail = ALEmail;
        this.ALmessage = ALMessage;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_recyclerview, parent, false);
        return new ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(Custom_Recycler_Adapter.ViewHolder holder, @SuppressLint({"Custom_Recycler_Adapter", "RecyclerView"}) int position) {
        holder.name.setText(ALname.get(position));
        holder.ALEmail.setText(ALEmail.get(position));
        holder.Message.setText(ALmessage.get(position));

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "click on item: " + ALname.get(position), Toast.LENGTH_LONG).show();
            Toast.makeText(view.getContext(), "click on item: " + ALEmail.get(position), Toast.LENGTH_LONG).show();
            Toast.makeText(view.getContext(), "click on item: " + ALmessage.get(position), Toast.LENGTH_LONG).show();
        });
    }
    @Override
    public int getItemCount() {
        return ALname.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView ALEmail;
        TextView Message;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.ALEmail = itemView.findViewById(R.id.Email);
            this.Message = itemView.findViewById(R.id.Message);
        }
    }
}
