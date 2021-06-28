package com.example.bigstep.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bigstep.model.MusicDescDatabaseModel;
import com.example.bigstep.roomdatabase.DatabaseClient;

import java.util.List;

public class HistoryFragmentViewModel extends ViewModel {
 //   private HistoryRepository historyRepository;
    private LiveData<List<MusicDescDatabaseModel>> allCategories;

    public HistoryFragmentViewModel() {

    }



    public LiveData<List<MusicDescDatabaseModel>> getAllHistory(Context context) {
        //adding to database
        LiveData<List<MusicDescDatabaseModel>> dataList = DatabaseClient.getInstance(context).getAppDatabase()
                .taskDao().getAll();




        return dataList;
    }
}
