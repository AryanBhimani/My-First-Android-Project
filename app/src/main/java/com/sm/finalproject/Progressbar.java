package com.sm.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Progressbar extends Activity {
    private Button Start, Stop;
    private ProgressBar Progressbar;
    boolean ProgressVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        ControlBindd();
        findViewById(R.id.imgBack).setOnClickListener(v -> onBackPressed());

        Progressbar.setVisibility(View.GONE);
        ProgressVisible = false;
        Start.setOnClickListener(v -> {
            if (!ProgressVisible) {
                ProgressVisible = true;
                Progressbar.setVisibility(View.VISIBLE);
            }
        });
        Stop.setOnClickListener(v -> {
            if (ProgressVisible) {
                Progressbar.setVisibility(View.GONE);
                ProgressVisible = false;
            }
        });
    }
    private void ControlBindd() {
        Progressbar = findViewById(R.id.Progressbar);
        Start = findViewById(R.id.idButton);
        Stop = findViewById(R.id.idButton1);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}