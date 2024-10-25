package com.sm.finalproject.Recording;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import com.sm.finalproject.Dashboard;
import com.sm.finalproject.R;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Audio extends Activity {
    static final int AUDIO_PERMISSION_CODE = 89;
    ImageButton list_btn, record_btn, files_btn;
    boolean is_recording = false;
    String recording_permission = Manifest.permission.RECORD_AUDIO;
    MediaRecorder mediaRecorder;
    String record_file;
    Chronometer chronoTimer;
    TextView record_file_name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andio_recorder);
        ControlBindd();
        findViewById(R.id.imgBack1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.record_button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if (is_recording) {
                    stop_recording();
                    record_btn.setImageDrawable(getResources().getDrawable(R.drawable.voice));
                    is_recording = false;
                } else {
                    if (checkAudioPermission()) {
                        start_recording();
                        record_btn.setImageDrawable(getResources().getDrawable(R.drawable.voice1));
                        is_recording = true;
                    }
                }
            }
        });
        findViewById(R.id.record_list_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AudioListView.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), R.string.files, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.files_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AudioFile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), R.string.files, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void start_recording() {
        chronoTimer.setBase(SystemClock.elapsedRealtime());
        chronoTimer.start();
        String rec_path = getExternalFilesDir("/").getAbsolutePath();
        @SuppressLint({"NewApi", "LocalSuppress"}) SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss", Locale.CANADA);
        Date date = new Date();
        record_file =  (getString(R.string.NameFileAudio) + simpleDateFormat.format(date) + (getString(R.string.mp3))) ;
        record_file_name.setText(getString(R.string.file) + record_file);
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(rec_path + "/" + record_file);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }
    private void stop_recording() {
        chronoTimer.stop();
        is_recording = false;
        record_file_name.setText(getString(R.string.Recording) + record_file);
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }
    private boolean checkAudioPermission() {
        if (ActivityCompat.checkSelfPermission(Audio.this, recording_permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(Audio.this, new String[]{recording_permission}, AUDIO_PERMISSION_CODE);
            return false;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (is_recording) {
            stop_recording();
        }
    }
    private void ControlBindd() {
        list_btn = findViewById(R.id.record_list_button);
        record_btn = findViewById(R.id.record_button);
        files_btn = findViewById(R.id.files_button);
        chronoTimer = findViewById(R.id.record_timer);
        record_file_name = findViewById(R.id.record_filename);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}