package com.sm.finalproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

public class MainActivity1 extends Activity {
    // creating object of ViewPager
    ViewPager mViewPager;
    // images array
    int[] images = {R.drawable.vwqi, R.drawable.superhummingbirds, R.drawable.gettyimages, R.drawable.download};
    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlBind();
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity1.this, images);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
    private void ControlBind() {
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.viewPagerMain);
    }
}