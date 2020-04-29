package com.example.user.myapplication;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class GPSTrackerHelper implements LocationListener {
    private static final String TAG = "GPSTrackerHelper";
    private final Context context;

    boolean isGPSEnabled = false;

    boolean isNetworkEnabled = false;
    Location currentLocation;
    double latitude;
    double longitude;

    static boolean isFirst = true;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100;

    private static final long MIN_TIME_BW_UPDATES = 0;

    protected LocationManager locationManager;

    public GPSTrackerHelper(Context context) {
        this.context = context;

        getLocation();

    }


    public Location getLocation() {

        locationManager = (LocationManager) context
                .getSystemService(Service.LOCATION_SERVICE);
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {

            return null;

        } else {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled) {

                    Log.i("GPS", "isGPSEnabled()");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {

                        Log.i("GPS", "first");

                        if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {

                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                            Log.i("GPS", "second");

                            currentLocation = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);


                            if (currentLocation != null) {

                                latitude = currentLocation.getLatitude();
                                longitude = currentLocation.getLongitude();
                                return currentLocation;
                            }

                            else {
                                return null;
                            }
                        } else {
                            Log.i("GPS", "third");


                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                            if (locationManager != null) {
                                currentLocation = locationManager
                                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                                if (currentLocation != null) {

                                    Log.i("GPS", "not null");

                                    latitude = currentLocation.getLatitude();
                                    longitude = currentLocation.getLongitude();

                                    return currentLocation;


                                } else {
                                    Log.i("GPS", "null");
                                    return null;
                                }
                            } else {
                                return null;
                            }

                        }
                    } else {
                        Log.i("GPS", "else GPS");
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            currentLocation = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                            if (currentLocation != null) {

                                latitude = currentLocation.getLatitude();
                                longitude = currentLocation.getLongitude();
                                return currentLocation;

                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                } else {
                    Log.i("GPS", "isWIFIEnabled()");

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        Log.i("GPS", "isWIFIEnabled()2");
                        currentLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                        if (currentLocation != null) {
                            latitude = currentLocation.getLatitude();
                            longitude = currentLocation.getLongitude();
                            return currentLocation;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }

        }
    }


    @Override
    public void onLocationChanged(Location location) {
        if(!isFirst) {
            ObservableObject.getInstance().updateValue();
        }
        else{
            isFirst = false;
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                onLocationChanged(getLocation());
            }
        });
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    public double getLatitude() {
        if (currentLocation != null) {
            latitude = currentLocation.getLatitude();
        }
        // return latitude
        return latitude;
    }
    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (currentLocation != null) {
            longitude = currentLocation.getLongitude();
        }
        // return longitude
        return longitude;
    }


    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTrackerHelper.this);
        }
    }
}