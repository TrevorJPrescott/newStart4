package com.trevorpc.newstart4.modelview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


class LocTracker {

    private static final String TAG = "LocTracker";

    LocationManager manager;
    LocationListener listener;
    Context context;
    Activity activity;


    public LocTracker(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public void fetchLocation(final Callback callback) {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: ");
                callback.onSuccess(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.requestLocationUpdates("gps", 5000, 0, listener);

        }
        else {
            manager.requestLocationUpdates("gps", 5000, 0, listener);
        }

    }

    public interface Callback {
        void onSuccess(Location location);
    }
}

