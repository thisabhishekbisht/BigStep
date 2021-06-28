package com.example.bigstep.retrofit;

import com.example.bigstep.model.MusicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiInterface {

    @GET("/search")
    Call<MusicResponse> getMusicRecord(@Query("term") String term , @Query("media") String media);


}