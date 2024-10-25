package com.sm.finalproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSLocation extends Activity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    TextView locaity,state,postcode,address,country,textLatLong;
    ResultReceiver resultReceiver;
    String getAddress,getcity,getstate,getcountry,getpostcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        resultReceiver = new AddressResultReceiver(new Handler());
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ControlBind();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GPSLocation.this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE);
                } else {
                    getCurrentLocation();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, R.string.Permission, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(GPSLocation.this).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(getApplicationContext())
                        .removeLocationUpdates(this);
                if (locationResult != null && locationResult.getLocations().size() > 0) {
                    int latestlocIndex = locationResult.getLocations().size() - 1;
                    double lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                    double longi = locationResult.getLocations().get(latestlocIndex).getLongitude();
                    textLatLong.setText(String.format("Latitude : %s Longitude: %s", lati, longi));
                    Geocoder geocoder;
                    List<Address> addresses;
                    geocoder = new Geocoder(GPSLocation.this, Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(lati, longi, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        try {
                            //todo get address based on lat long
                            getAddress = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            getcity = addresses.get(0).getLocality();
                            getstate = addresses.get(0).getAdminArea();
                            getcountry = addresses.get(0).getCountryName();
                            getpostcode = addresses.get(0).getPostalCode();
                            address.setText(getAddress);
                            locaity.setText(getcity);
                            state.setText(getstate);
                            country.setText(getcountry);
                            postcode.setText(getpostcode);
                            //    Log.d("Location-261", "" + StrAddress);
                        } catch (Exception e) {
//                            strAddress = "";
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   /* android.location.Location location = new android.location.Location(String.valueOf(R.string.providerNA));
                    location.setLongitude(longi);
                    location.setLatitude(lati);
                    fetchaddressfromlocation(location);*/
                }
            }
            }, Looper.getMainLooper());
    }
    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constants.SUCCESS_RESULT) {
                /*address.setText(resultData.getString(Constants.ADDRESS));
                locaity.setText(resultData.getString(Constants.LOCAITY));
                state.setText(resultData.z(Constants.STATE));
                country.setText(resultData.getString(Constants.COUNTRY));
                postcode.setText(resultData.getString(Constants.POST_CODE));*/
            } else {
                Toast.makeText(GPSLocation.this, resultData.getString(Constants.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void fetchaddressfromlocation(android.location.Location location) {
        Intent intent = new Intent(this, FetchAddressIntentServices.class);
        intent.putExtra(Constants.RECEVIER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }
    @SuppressLint("WrongViewCast")
    private void ControlBind() {
        textLatLong = findViewById(R.id.textLatLong);
        address = findViewById(R.id.textaddress);
        locaity = findViewById(R.id.textlocality);
        postcode = findViewById(R.id.textcode);
        country = findViewById(R.id.textcountry);
        state = findViewById(R.id.textstate);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}