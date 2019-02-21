package com.trevorpc.newstart4.model;

import android.util.Log;

import com.trevorpc.newstart4.model.object.FullResponse;
import com.trevorpc.newstart4.model.object.Response;
import com.trevorpc.newstart4.web.MyAPI;
import com.trevorpc.newstart4.web.RetrofitClient;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Repository {
    MyAPI api;
    private static final String TAG = "Repository";

    public Repository() {
        RetrofitClient client = new RetrofitClient();
        Retrofit retrofit = client.getOurInstance();
        api = retrofit.create(MyAPI.class);
    }

    public void fetchData(Double latitude, Double longitude,final Callback callback) {
        api.getResponse(latitude,longitude,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FullResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { Log.d(TAG, "onSubscribe: "); }

                    @Override
                    public void onSuccess(FullResponse fullResponse) {
                        Log.d(TAG, "onSuccess: ");
                        callback.onSuccess(fullResponse.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        callback.onFailure("Error in fetchData");
                    }
                });
    }

    public interface Callback {
        void onSuccess(List<Response> responseList);
        void onFailure(String error);
    }

}
