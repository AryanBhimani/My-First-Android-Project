package com.sm.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class RecyclerView extends Activity {
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ArrayList<String> ALexamName= new ArrayList<>();
    ArrayList<String> ALEmail = new ArrayList<>();
    ArrayList<String> ALexamMessage = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ControlBind();
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ALexamName.add("Mathan");
        ALexamName.add("Stanlee");
        ALexamName.add("Aryan");
        ALexamName.add("Yug");
        ALexamName.add("Krish");
        ALexamName.add("Shyam");
        ALexamName.add("Arpan");
        ALexamName.add("Dhaval");
        ALexamName.add("Harsh");
        ALexamName.add("Pranav");
        ALexamName.add("Vad");
        ALexamName.add("Vasu");

        ALEmail.add("Mathan@email.com");
        ALEmail.add("Stanlee@email.com");
        ALEmail.add("Aryan@email.com");
        ALEmail.add("Yug@email.com");
        ALEmail.add("Krish@email.com");
        ALEmail.add("Shyam@email.com");
        ALEmail.add("Arpan@email.com");
        ALEmail.add("Dhaval@email.com");
        ALEmail.add("Harsh@email.com");
        ALEmail.add("Pranav@email.com");
        ALEmail.add("Vad@email.com");
        ALEmail.add("Vasu@email.com");

        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");
        ALexamMessage.add("1234567890");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Custom_Recycler_Adapter customAdapter = new Custom_Recycler_Adapter(RecyclerView.this,ALexamName,ALEmail,ALexamMessage);
        recyclerView.setAdapter(customAdapter);
    }
    void ControlBind() {
        recyclerView = findViewById(R.id.recycler_view);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}