package com.example.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class RunningActivity extends AppCompatActivity implements Observer, OnMapReadyCallback {

    static GoogleMap m_map;
    MapFragment mapFragment;
    TextView kmTextView;
    Button startButton;
    boolean isSessionRunning = false;
    boolean mapReady = false;
    GPSTrackerHelper gpsTrackerHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_layout);

        final Activity activity = this;



        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapMain);
        mapFragment.getMapAsync(this);
        kmTextView = (TextView)findViewById(R.id.kmTextView);
        startButton = (Button)findViewById(R.id.startButton);

        ObservableObject.getInstance().addObserver(this);

        if ((ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        else{
            isLocationServicesEnabled();
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isSessionRunning){


                    Log.i("color", "changedToRed");
                    isSessionRunning = false;
                    GPSTrackerHelper.isFirst = true;

                    if(gpsTrackerHelper != null) {
                        gpsTrackerHelper.stopUsingGPS();
                        gpsTrackerHelper = null;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String currentDateandTime = sdf.format(new Date());
                    DBHelperTapeta sqLiteDatabaseHelper = new DBHelperTapeta(getApplicationContext());

                    Rekord rekord = new Rekord(String.valueOf(sqLiteDatabaseHelper.getAllRekords().size() + 1)
                            , currentDateandTime, kmTextView.getText().toString());

                    sqLiteDatabaseHelper.addRekord(rekord);

                    kmTextView.setText("0.0");
                    startButton.setBackgroundResource(R.drawable.map_button);
                }
                else {

                    if ((ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED)) {

                        ActivityCompat.requestPermissions(
                                activity,
                                new String[]{
                                        android.Manifest.permission.ACCESS_FINE_LOCATION},
                                1);
                    } else {

                        isLocationServicesEnabled();
                        isSessionRunning = true;

                        gpsTrackerHelper = new GPSTrackerHelper(getApplicationContext());

                        Log.i("color", "changedToGreen");
                        startButton.setBackgroundResource(R.drawable.map_button_red);
                    }
                }
            }
        });
    }

    @Override
        public void update(Observable o, Object arg) {

       kmTextView.setText(String.format("%.1f%n", powieksKm(kmTextView.getText().toString().replaceAll(",", "."))));
    }

    public double powieksKm(String kms){
        double kmsD = Double.parseDouble(kms);
        return kmsD + 0.1;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            m_map.setMyLocationEnabled(true);


            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                LatLng coordinate = new LatLng(latitude, longitude);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
                m_map.animateCamera(yourLocation);
            }
        }
    }

    public void isLocationServicesEnabled() {
        final ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetworkEnabled = cm.getActiveNetworkInfo() != null;

        LocationManager lm = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if( (!gps_enabled && !network_enabled)
                || !network_enabled && isNetworkEnabled
                || !isNetworkEnabled && !gps_enabled) {
            // notify user
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Location Services not enabled");
            dialog.setMessage("Application can't get your location if Location Services are disabled. Please, enable it.");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                }
            });
            dialog.show();
        }
    }
}
