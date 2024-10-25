package com.sm.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.sm.finalproject.Recording.Audio;

public class Dashboard extends Activity {
    ImageView imageView1, imageView2, imageView3, imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ViewPager mViewPager;
    int[] images = {R.drawable.gettyimages, R.drawable.superhummingbirds, R.drawable.vwqi, R.drawable.download};
    ViewPagerAdapter mViewPagerAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ControlBindd();
        mViewPagerAdapter = new ViewPagerAdapter(Dashboard.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.ListView), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ListViewActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.GridView), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), GridViewActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.RecyclerView), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), RecyclerView.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.camera), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), CameraCaptureImage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.AutoComplete), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), AutoComplete.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.googlemaps), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.Location), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), GPSLocation.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.AndioRecorder), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Audio.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, getString(R.string.Progressbar), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Progressbar.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
    }
    private void ControlBindd() {
        imageView1 = findViewById(R.id.ListView);
        imageView2 = findViewById(R.id.GridView);
        imageView3 = findViewById(R.id.RecyclerView);
        imageView4 = findViewById(R.id.Camera);
        imageView5 = findViewById(R.id.autoCompleteTextView1);
        imageView6 = findViewById(R.id.Maps);
        imageView7 = findViewById(R.id.Location);
        imageView8 = findViewById(R.id.AudioRecorder);
        imageView9 = findViewById(R.id.Progressbar);
        mViewPager = findViewById(R.id.viewPagerMain);
    }
}