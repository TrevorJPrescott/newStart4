package com.trevorpc.newstart4.web;



import com.trevorpc.newstart4.model.object.FullResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyAPI {
    @GET("iss-pass.json")
    Single<FullResponse> getResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("n") int number);
}
