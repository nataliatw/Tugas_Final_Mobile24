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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.f1raceandcircuitinformation.ApiConfig;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.Fragment.HomeFragment;
import com.example.f1raceandcircuitinformation.R;
import com.example.f1raceandcircuitinformation.Response.Driver;
import com.example.f1raceandcircuitinformation.Response.DriverResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriversActivity extends AppCompatActivity {
    private ImageView ivDriver, ivFlag, ivNext;
    private TextView tvName, tvBirthDate, tvNationality, tvCode, tvNumber;
    private Toolbar toolbar;
    private Executor executor;
    private ProgressBar progressBar;
    private LinearLayout layout1, layout2, layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        ivDriver = findViewById(R.id.iv_driver);
        ivFlag = findViewById(R.id.iv_bendera);
        ivNext = findViewById(R.id.iv_next);
        tvName = findViewById(R.id.tv_nama);
        tvBirthDate = findViewById(R.id.tv_tanggal);
        tvNationality = findViewById(R.id.tv_nationality);
        tvCode = findViewById(R.id.tv_code);
        tvNumber = findViewById(R.id.tv_number);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressbar);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        executor = Executors.newSingleThreadExecutor();

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String driverId = intent.getStringExtra("driverId");
        if (driverId != null) {
            loadDriverDetails(driverId);
        } else {
            Toast.makeText(this, "Driver ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDriverDetails(String driverId) {
        executor.execute(() -> {
            progressBar.setVisibility(View.VISIBLE);
            ivDriver.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvBirthDate.setVisibility(View.GONE);
            ivFlag.setVisibility(View.GONE);
            tvNationality.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);

            ApiConfig.getApiService().getDriverDetail(driverId).enqueue(new Callback<DriverResponse>() {
                @Override
                public void onResponse(@NonNull Call<DriverResponse> call, @NonNull Response<DriverResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        ivDriver.setVisibility(View.VISIBLE);
                        tvName.setVisibility(View.VISIBLE);
                        tvBirthDate.setVisibility(View.VISIBLE);
                        ivFlag.setVisibility(View.VISIBLE);
                        tvNationality.setVisibility(View.VISIBLE);
                        layout1.setVisibility(View.VISIBLE);
                        layout2.setVisibility(View.VISIBLE);
                        layout3.setVisibility(View.VISIBLE);
                        Log.d("DriversActivity", "Response: " + new Gson().toJson(response.body()));
                        DriverResponse.MRData driverMRData = response.body().getMRData();
                        if (driverMRData != null) {
                            DriverResponse.MRData.DriverTable driverTable = driverMRData.getDriverTable();
                            if (driverTable != null) {
                                List<Driver> drivers = driverTable.getDrivers();
                                if (drivers != null && !drivers.isEmpty()) {
                                    Driver driver = drivers.get(0);
                                    runOnUiThread(() -> updateUI(driver));
                                } else {
                                    Log.e("DriversActivity", "Driver data is empty");
                                    runOnUiThread(() -> Toast.makeText(DriversActivity.this, "No driver data available", Toast.LENGTH_SHORT).show());
                                }
                            } else {
                                Log.e("DriversActivity", "Driver table is null");
                                runOnUiThread(() -> Toast.makeText(DriversActivity.this, "Driver table is null", Toast.LENGTH_SHORT).show());
                            }
                        } else {
                            Log.e("DriversActivity", "MRData is null");
                            runOnUiThread(() -> Toast.makeText(DriversActivity.this, "MRData is null", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        Log.e("DriversActivity", "Failed to fetch driver data: " + response.message());
                        runOnUiThread(() -> Toast.makeText(DriversActivity.this, "Failed to get driver data", Toast.LENGTH_SHORT).show());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DriverResponse> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.e("DriversActivity", "Error fetching driver data", t);
                    runOnUiThread(() -> Toast.makeText(DriversActivity.this, "Error fetching driver data", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }

    private void updateUI(Driver driver) {
        tvName.setText(driver.getGivenName() + " " + driver.getFamilyName());
        tvBirthDate.setText(driver.getDateOfBirth());
        tvNationality.setText(driver.getNationality());
        tvCode.setText(driver.getCode());
        tvNumber.setText(driver.getPermanentNumber());

        ivNext.setOnClickListener(v -> {
            String url = driver.getUrl();
            if (url != null && !url.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                Toast.makeText(DriversActivity.this, "No URL available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}


