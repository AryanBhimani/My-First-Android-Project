package com.sm.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sm.finalproject.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<LatLng> locationArrayList;
     ArrayList<String> name=new ArrayList<>();
    LatLng varachha = new LatLng(21.2021, 72.8673);
    LatLng motavarachha = new LatLng(21.2408, 72.8806);
    LatLng kamrej = new LatLng(21.2695, 72.9577);
    LatLng adajan = new LatLng(21.1959, 72.7933);
    LatLng vesu = new LatLng(21.1418, 72.7709);
    LatLng Bardoli = new LatLng(21.1418, 73.1122);
    LatLng Kadodara = new LatLng(21.1717, 72.9627);
    LatLng un = new LatLng(21.1114, 72.8658);
    LatLng palsana = new LatLng(21.0863, 72.9779);
    LatLng sampura = new LatLng(21.2436, 73.1077);
    LatLng digas = new LatLng(21.2653, 73.0516);
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationArrayList = new ArrayList<>();
        locationArrayList.add(varachha);
        locationArrayList.add(motavarachha);
        locationArrayList.add(kamrej);
        locationArrayList.add(adajan);
        locationArrayList.add(vesu);
        locationArrayList.add(Bardoli);
        locationArrayList.add(Kadodara);
        locationArrayList.add(un);
        locationArrayList.add(palsana);
        locationArrayList.add(sampura);
        locationArrayList.add(digas);

        name = new ArrayList<>();
        name.add("Varachha");
        name.add("Motavarachha");
        name.add("Kamrej");
        name.add("Adajan");
        name.add("Vesu");
        name.add("Bardoli");
        name.add("Kadodara");
        name.add("Un");
        name.add("Palsana");
        name.add("Sampura");
        name.add("Digas");
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < locationArrayList.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(String.valueOf(name.get(i))));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
        }

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}