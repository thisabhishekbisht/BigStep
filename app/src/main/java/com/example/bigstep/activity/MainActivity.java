package com.example.bigstep.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bigstep.R;

import com.example.bigstep.fragment.HistoryFragment;
import com.example.bigstep.fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private boolean doubleBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0.0f);
        getSupportActionBar().setTitle("iTunes PlayList");
        tabLayout = findViewById(R.id.tabMain);
        viewPager = findViewById(R.id.vpMain);

        setViewPager();

    }

    private void setViewPager() {

        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_video));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_history));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        Adapter adapter = new Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    class Adapter extends FragmentPagerAdapter {

        public Adapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new VideoFragment();
                case 1:
                    return new HistoryFragment();
                default:
                    return null; // Problem occurs at this condition!
            }

        }

        @Override
        public int getCount() {
            return tabLayout.getTabCount();
        }


    }


    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        if (tabLayout.getSelectedTabPosition() > 0) {
            tabLayout.selectTab(tabLayout.getTabAt(0));
        } else {
            if (doubleBackPressed) {
                finishAffinity();
            } else {
                doubleBackPressed = true;
                Toast.makeText(this, R.string.press_back_to_exit, Toast.LENGTH_SHORT).show();
                //delay
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Your Code
                        doubleBackPressed = false;
                    }
                }, 2000);

            }
        }
    }

}