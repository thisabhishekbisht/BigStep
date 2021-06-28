package com.example.bigstep.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.bigstep.model.Music;
import com.example.bigstep.model.MusicResponse;
import com.example.bigstep.retrofit.RetrofitApiInterface;
import com.example.bigstep.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicRepository {
    private ArrayList<Music> music=new ArrayList<>();
        private MutableLiveData<List<Music>> mutableLiveData=new MutableLiveData<>();


        public MusicRepository() {

        }

        public MutableLiveData<List<Music>> getMutableLiveData() {

            RetrofitApiInterface movieDataService = RetrofitInstance.getService();

            Call<MusicResponse> call = movieDataService.getMusicRecord("Michael+jackson", "musicVideo");

            call.enqueue(new Callback<MusicResponse>() {
                @Override
                public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                    MusicResponse movieDBResponse = response.body();


                    if (movieDBResponse != null && movieDBResponse.getMusic() != null) {

                        music = (ArrayList<Music>) movieDBResponse.getMusic();
                        mutableLiveData.setValue(music);
                    }
                }

                @Override
                public void onFailure(Call<MusicResponse> call, Throwable t) {



                }
            });

            return mutableLiveData;
        }
}
