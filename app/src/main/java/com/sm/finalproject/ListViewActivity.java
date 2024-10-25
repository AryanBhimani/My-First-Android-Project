package com.sm.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends Activity {
    ListView simpleList;
    ArrayList<String> ALName = new ArrayList<>();
    ArrayList<String> ALEmail = new ArrayList<>();
    ArrayList<String> ALMob_no = new ArrayList<>();
    int flags[] = {R.drawable.mathan, R.drawable.stanlee, R.drawable.aryannew, R.drawable.aryan, R.drawable.aryan, R.drawable.aryan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ControlBind();
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ALName.add("Mathan");
        ALName.add("Stanlee");
        ALName.add("Aryan");
        ALName.add("Yug");
        ALName.add("Krish");
        ALName.add("Shyam");

        ALEmail.add("Mathan@email.com");
        ALEmail.add("Stanlee@email.com");
        ALEmail.add("Aryan@email.com");
        ALEmail.add("Yug@email.com");
        ALEmail.add("Krish@email.com");
        ALEmail.add("Shyam@email.com");

        ALMob_no.add("1234567890");
        ALMob_no.add("1234567890");
        ALMob_no.add("1234567890");
        ALMob_no.add("1234567890");
        ALMob_no.add("1234567890");
        ALMob_no.add("1234567890");

        Custom_ListView_Adapter ListView = new Custom_ListView_Adapter(getApplicationContext(), ALName, ALEmail, ALMob_no, flags);
        simpleList.setAdapter(ListView);
    }

    void ControlBind() {
        simpleList = findViewById(R.id.simpleListView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}