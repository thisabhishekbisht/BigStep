package com.example.bigstep.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bigstep.model.Music;
import com.example.bigstep.repository.MusicRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {
    private MusicRepository musicRepository;

    public HomeFragmentViewModel() {
        musicRepository=new MusicRepository();
    }

    public LiveData<List<Music>> getAllMovies(){

        return musicRepository.getMutableLiveData();
    }


}
