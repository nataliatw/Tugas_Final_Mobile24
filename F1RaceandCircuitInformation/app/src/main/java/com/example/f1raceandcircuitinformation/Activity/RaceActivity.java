package com.example.f1raceandcircuitinformation.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.f1raceandcircuitinformation.ApiConfig;
import com.example.f1raceandcircuitinformation.ApiService;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.R;
import com.example.f1raceandcircuitinformation.Response.Circuit;
import com.example.f1raceandcircuitinformation.Response.Race;
import com.example.f1raceandcircuitinformation.Response.RaceResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaceActivity extends AppCompatActivity {
    private TextView tvPractice1Date, tvPractice1Time, tvPractice2Date, tvPractice2Time, tvPractice3Date, tvPractice3Time, tvRaceDate, tvRaceTime, tvQualifyingDate, tvQualifyingTime, tvNameCircuit, tvLocationCircuit;
    private ImageView ivRaceInfo, ivCircuitInfo, ivF1Tv, ivCircuit, ivLocation;
    private Race selectedRace;
    private DatabaseHelper databaseHelper;
    private ProgressBar progressBar;
    private LinearLayout layout1, layout2, layout3, layout4, layout6, layout7, layout8, layout9;
    private TextView tv1, tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);

        initViews();
        databaseHelper = new DatabaseHelper(this);

        String circuitId = getIntent().getStringExtra("raceId");
        if (circuitId != null) {
            loadRaceDetails(circuitId);
        }

        ivRaceInfo.setOnClickListener(v -> openRaceInfo(selectedRace));
        ivCircuitInfo.setOnClickListener(v -> openCircuitInfo(selectedRace != null ? selectedRace.getCircuit() : null));
        ivF1Tv.setOnClickListener(v -> openF1Tv());
    }

    private void initViews() {
        tvPractice1Date = findViewById(R.id.tv_prac1date);
        tvPractice1Time = findViewById(R.id.tv_prac1time);
        tvPractice2Date = findViewById(R.id.tv_prac2date);
        tvPractice2Time = findViewById(R.id.tv_prac2time);
        tvPractice3Date = findViewById(R.id.tv_prac3date);
        tvPractice3Time = findViewById(R.id.tv_prac3time);
        tvRaceDate = findViewById(R.id.tv_racedate);
        tvRaceTime = findViewById(R.id.tv_racetime);
        tvQualifyingDate = findViewById(R.id.tv_quadate);
        tvQualifyingTime = findViewById(R.id.tv_quatime);
        tvNameCircuit = findViewById(R.id.tv_namecircuit);
        tvLocationCircuit = findViewById(R.id.tv_locationcircuit);
        ivRaceInfo = findViewById(R.id.iv_raceinfo);
        ivCircuitInfo = findViewById(R.id.iv_circuitinfo);
        ivF1Tv = findViewById(R.id.iv_f1tv);
        ivCircuit = findViewById(R.id.iv_circuit);
        ivLocation = findViewById(R.id.iv_circuitrace);
        progressBar = findViewById(R.id.progressbar);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout6 = findViewById(R.id.layout6);
        layout7 = findViewById(R.id.layout7);
        layout8 = findViewById(R.id.layout8);
        layout9 = findViewById(R.id.layout9);
        tv1 = findViewById(R.id.tvtrack);
        tv2 = findViewById(R.id.tvcircuit);
    }

    private void loadRaceDetails(String circuitId) {
        progressBar.setVisibility(View.VISIBLE); // Tampilkan ProgressBar
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
        layout6.setVisibility(View.GONE);
        layout7.setVisibility(View.GONE);
        layout8.setVisibility(View.GONE);
        layout9.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        ivCircuit.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
        tvNameCircuit.setVisibility(View.GONE);
        tvLocationCircuit.setVisibility(View.GONE);

        ApiService apiService = ApiConfig.getApiService();
        apiService.getRaces().enqueue(new Callback<RaceResponse>() {
            @Override
            public void onResponse(Call<RaceResponse> call, Response<RaceResponse> response) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE); // Sembunyikan ProgressBar
                    if (response.isSuccessful() && response.body() != null) {
                        for (Race race : response.body().getMRData().getRaceTable().getRaces()) {
                            if (race.getCircuit().getCircuitId().equals(circuitId)) {
                                selectedRace = race;
                                updateUIWithRaceDetails(race);
                                layout1.setVisibility(View.VISIBLE);
                                layout2.setVisibility(View.VISIBLE);
                                layout3.setVisibility(View.VISIBLE);
                                layout4.setVisibility(View.VISIBLE);
                                layout6.setVisibility(View.VISIBLE);
                                layout7.setVisibility(View.VISIBLE);
                                layout8.setVisibility(View.VISIBLE);
                                layout9.setVisibility(View.VISIBLE);
                                tv1.setVisibility(View.VISIBLE);
                                tv2.setVisibility(View.VISIBLE);
                                ivCircuit.setVisibility(View.VISIBLE);
                                ivLocation.setVisibility(View.VISIBLE);
                                tvNameCircuit.setVisibility(View.VISIBLE);
                                tvLocationCircuit.setVisibility(View.VISIBLE);
                                databaseHelper.insertHistory(circuitId, "race", getCurrentDateTime());
                                break;
                            }
                        }
                    } else {
                        showErrorMessage("Failed to get race details");
                    }
                });
            }

            @Override
            public void onFailure(Call<RaceResponse> call, Throwable t) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE); // Sembunyikan ProgressBar jika terjadi kegagalan
                    Log.e("RaceActivity", "Error fetching race details", t);
                    showErrorMessage("Error fetching race details");
                });
            }
        });
    }

    private void updateUIWithRaceDetails(Race race) {
        tvRaceDate.setText(race.getDate());
        tvRaceTime.setText(race.getTime());

        if (race.getFirstPractice() != null) {
            tvPractice1Date.setText(race.getFirstPractice().getDate());
            tvPractice1Time.setText(race.getFirstPractice().getTime());
        } else {
            tvPractice1Date.setText("N/A");
            tvPractice1Time.setText("N/A");
        }

        if (race.getSecondPractice() != null) {
            tvPractice2Date.setText(race.getSecondPractice().getDate());
            tvPractice2Time.setText(race.getSecondPractice().getTime());
        } else {
            tvPractice2Date.setText("N/A");
            tvPractice2Time.setText("N/A");
        }

        if (race.getThirdPractice() != null) {
            tvPractice3Date.setText(race.getThirdPractice().getDate());
            tvPractice3Time.setText(race.getThirdPractice().getTime());
        } else {
            tvPractice3Date.setText("N/A");
            tvPractice3Time.setText("N/A");
        }

        if (race.getQualifying() != null) {
            tvQualifyingDate.setText(race.getQualifying().getDate());
            tvQualifyingTime.setText(race.getQualifying().getTime());
        } else {
            tvQualifyingDate.setText("N/A");
            tvQualifyingTime.setText("N/A");
        }

        tvNameCircuit.setText(race.getCircuit().getCircuitName());
        tvLocationCircuit.setText(race.getCircuit().getLocation().getLocality() + ", " + race.getCircuit().getLocation().getCountry());
    }

    private void openRaceInfo(Race race) {
        if (race != null) {
            String url = race.getUrl();
            if (url != null && !url.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                Toast.makeText(RaceActivity.this, "No URL available", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCircuitInfo(Circuit circuit) {
        if (circuit != null) {
            String url = circuit.getUrl();
            if (url != null && !url.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                Toast.makeText(RaceActivity.this, "No URL available", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openF1Tv() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://f1tv.formula1.com/"));
        startActivity(intent);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void showErrorMessage(String message) {
        Toast.makeText(RaceActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

