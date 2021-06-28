package com.example.bigstep.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bigstep.model.Music;
import com.example.bigstep.model.MusicDescDatabaseModel;
import com.example.bigstep.model.MusicResponse;
import com.example.bigstep.retrofit.RetrofitApiInterface;
import com.example.bigstep.retrofit.RetrofitInstance;
import com.example.bigstep.roomdatabase.AppDatabase;
import com.example.bigstep.roomdatabase.DatabaseClient;
import com.example.bigstep.roomdatabase.DatabaseDao;

import java.util.List;


public class HistoryRepository {
    private MutableLiveData<List<MusicDescDatabaseModel>> mutableLiveData = new MutableLiveData<>();
private DatabaseDao databaseDao;



    public HistoryRepository() {

    }



    public LiveData<List<MusicDescDatabaseModel>> getCategories() {
        return databaseDao.getAll();
    }
}
