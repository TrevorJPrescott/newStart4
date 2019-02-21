package com.trevorpc.newstart4.modelview;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.trevorpc.newstart4.model.Repository;
import com.trevorpc.newstart4.model.object.Response;

import java.util.List;


public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LocTracker tracker;
    private Adapter adapter;
    Context context;

    private static final String TAG = "MainViewModel";

    Double latitude, longitude;

    public MainViewModel(@NonNull Application application, Activity activity) {
        super(application);
        repository = new Repository();
        context = application.getApplicationContext();
        tracker = new LocTracker(activity);
        adapter = new Adapter();


    }

    public void init(Callback callback ) {
        tracker.fetchLocation(new LocTracker.Callback() {
            @Override
            public void onSuccess(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d(TAG, "onSuccess: " + latitude + " " + longitude);
                repository.fetchData(latitude, longitude, new Repository.Callback() {
                    @Override
                    public void onSuccess(List<Response> responseList) {
                        adapter.upDateList(responseList);
                        Log.d(TAG, "onSuccess: " + responseList.size());
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
            }
        });

    }

    public interface Callback {
        void onSuccess(Adapter adapter);
    }

}
