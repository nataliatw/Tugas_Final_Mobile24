package com.example.f1raceandcircuitinformation.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.f1raceandcircuitinformation.Fragment.HistoryFragment;
import com.example.f1raceandcircuitinformation.Fragment.HomeFragment;
import com.example.f1raceandcircuitinformation.R;

public class HomeActivity extends AppCompatActivity {

    ImageView home, bookmark, history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.iv_home);
        history = findViewById(R.id.iv_history);

        FragmentManager fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();

        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment)
                    .commit();
        }



        history.setOnClickListener(v -> {
            HistoryFragment historyFragment = new HistoryFragment();
            Fragment f = fragmentManager.findFragmentByTag(HistoryFragment.class.getSimpleName());
            if (!(f instanceof HistoryFragment)){
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, historyFragment)
                        .addToBackStack(null)
                        .commit();
            }

        });

        home.setOnClickListener(v -> {
            HomeFragment homeFragment1 = new HomeFragment();
            Fragment h = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
            if (!(h instanceof HistoryFragment)){
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, homeFragment1)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}