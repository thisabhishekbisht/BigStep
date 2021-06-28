package com.example.bigstep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bigstep.R;
import com.example.bigstep.databinding.ActivityMusicDescBinding;
import com.example.bigstep.model.Music;
import com.example.bigstep.roomdatabase.DatabaseClient;
import com.example.bigstep.model.MusicDescDatabaseModel;
import com.example.bigstep.roomdatabase.DatabaseDao;

import java.util.List;

public class MusicDescActivity extends AppCompatActivity {

    private Music music;
    private ActivityMusicDescBinding activityMusicDescBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_desc);

        activityMusicDescBinding = DataBindingUtil.setContentView(this, R.layout.activity_music_desc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.hasExtra("music")) {

            music = getIntent().getParcelableExtra("music");
            activityMusicDescBinding.setMusic(music);
            getSupportActionBar().setTitle(music.getTrackName());


        }


        insertDataIntoDatabase();


    }

    private void insertDataIntoDatabase() {
        //

        String imageUrl = music.getArtworkUrl100();
        String trackName = music.getTrackName();
        String artistName = music.getArtistName();
        Double price = music.getTrackPrice();
        String genere = music.getPrimaryGenreName();

        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                .taskDao().insert(new MusicDescDatabaseModel(0,imageUrl,trackName,artistName,price,genere));


    Toast.makeText(MusicDescActivity.this, "Sucessfully Inserted Record", Toast.LENGTH_SHORT).show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}