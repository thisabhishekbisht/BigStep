package com.example.bigstep.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bigstep.R;
import com.example.bigstep.adapter.HistoryAdapter;
import com.example.bigstep.model.Music;
import com.example.bigstep.roomdatabase.DatabaseClient;
import com.example.bigstep.model.MusicDescDatabaseModel;
import com.example.bigstep.viewModel.HistoryFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerViewHistory;
    private HistoryAdapter historyAdapter;
    ArrayList<MusicDescDatabaseModel> dataList;
    private HistoryFragmentViewModel historyFragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        //
        historyFragmentViewModel = new ViewModelProvider(this).get(HistoryFragmentViewModel.class);

        //
        dataList = new ArrayList<>();

        recyclerViewHistory = view.findViewById(R.id.recyclerViewHistory);
        historyFragmentViewModel.getAllHistory(getContext()).observe(this, historyFromLiveData -> {
            dataList = (ArrayList<MusicDescDatabaseModel>) historyFromLiveData;
            showOnRecyclerView();
        });

        return view;
    }



    private void showOnRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(llm);
        recyclerViewHistory.setItemAnimator(new DefaultItemAnimator());

        historyAdapter = new HistoryAdapter(getContext(), dataList);
        recyclerViewHistory.setAdapter(historyAdapter);

    }

}