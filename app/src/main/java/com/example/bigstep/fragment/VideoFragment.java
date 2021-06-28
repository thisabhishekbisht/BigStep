package com.example.bigstep.fragment;

import android.content.res.Configuration;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bigstep.R;
import com.example.bigstep.adapter.MusicAdapter;
import com.example.bigstep.databinding.FragmentVideoBinding;
import com.example.bigstep.model.Music;
import com.example.bigstep.viewModel.HomeFragmentViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private TextView tv_noData;
    private FragmentVideoBinding fragmentVideoBinding;
    private ArrayList<Music> music;
    private RecyclerView recyclerView;
    private MusicAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public HomeFragmentViewModel homeFragmentViewModel;


    public VideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        fragmentVideoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);

        homeFragmentViewModel = new ViewModelProvider(getActivity()).get(HomeFragmentViewModel.class);


        swipeRefreshLayout = fragmentVideoBinding.swipeLayout;
        tv_noData = fragmentVideoBinding.tvNoData;
        getPopularMovies();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());

        return fragmentVideoBinding.getRoot();

    }

    public void getPopularMovies() {
// set Refresh layout false
        swipeRefreshLayout.setRefreshing(false);
        homeFragmentViewModel.getAllMovies().observe(getActivity(), moviesFromLiveData -> {
            music = (ArrayList<Music>) moviesFromLiveData;
            showOnRecyclerView();
        });
    }

    private void showOnRecyclerView() {

        recyclerView = fragmentVideoBinding.rvSongs;
        movieAdapter = new MusicAdapter(getContext(), music);



        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

        if (music.isEmpty()) {
            tv_noData.setVisibility(View.VISIBLE);
        } else {
            tv_noData.setVisibility(View.GONE);
        }

    }
}