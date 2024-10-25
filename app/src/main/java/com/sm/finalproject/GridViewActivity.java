package com.sm.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class GridViewActivity extends Activity {

    GridView idGVcourses;
    ArrayList<String> ALName  = new ArrayList<>();
    String Name [] = {"mathan", "stanlee", "aryan","reminder","petition"};
    int flags[] = {R.drawable.mathan, R.drawable.stanlee, R.drawable.aryannew,R.drawable.reminder,R.drawable.petition};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        ControlBind();
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ALName.add("mathan");
        ALName.add("stanlee");
        ALName.add("aryan");
        ALName.add("reminder");
        ALName.add("petition");
        Custom_GridView_Adapter GridView = new Custom_GridView_Adapter(getApplicationContext(),ALName,flags);
        idGVcourses.setAdapter((ListAdapter) GridView);
    }
    void ControlBind() {
        idGVcourses = findViewById(R.id.idGVcourses);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}