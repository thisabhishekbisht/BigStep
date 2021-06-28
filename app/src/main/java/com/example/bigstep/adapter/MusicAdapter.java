package com.example.bigstep.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigstep.R;
import com.example.bigstep.activity.MusicDescActivity;
import com.example.bigstep.databinding.MusicListItemBinding;
import com.example.bigstep.model.Music;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context context;
    private ArrayList<Music> musicArrayList;

    public MusicAdapter(Context context, ArrayList<Music> musicArrayList) {
        this.context = context;
        this.musicArrayList = musicArrayList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MusicListItemBinding musicListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.music_list_item, parent, false);

        return new MusicViewHolder(musicListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

        Music music = musicArrayList.get(position);
        holder.musicListItemBinding.setMusic(music);
    }

    @Override
    public int getItemCount() {
        return musicArrayList.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        private MusicListItemBinding musicListItemBinding;

        public MusicViewHolder(@NonNull MusicListItemBinding musicListItemBinding) {
            super(musicListItemBinding.getRoot());
            this.musicListItemBinding = musicListItemBinding;

            musicListItemBinding.getRoot().setOnClickListener(v -> {

                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    Music selctedMusic = musicArrayList.get(position);
                    Intent intent = new Intent(context, MusicDescActivity.class);
                    intent.putExtra("music", selctedMusic);
                    context.startActivity(intent);

                }
            });
        }

    }
}
