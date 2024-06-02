package com.example.f1raceandcircuitinformation.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.f1raceandcircuitinformation.Adapter.DriversAdapter;
import com.example.f1raceandcircuitinformation.Adapter.RaceAdapter;
import com.example.f1raceandcircuitinformation.ApiConfig;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.R;
import com.example.f1raceandcircuitinformation.Response.Driver;
import com.example.f1raceandcircuitinformation.Response.DriverResponse;
import com.example.f1raceandcircuitinformation.Response.Race;
import com.example.f1raceandcircuitinformation.Response.RaceResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView driversRecyclerView;
    private DriversAdapter driversAdapter;
    private ArrayList<Driver> driverList;
    private RecyclerView racesRecyclerView;
    private RaceAdapter raceAdapter;
    private ArrayList<Race> raceList;
    private ImageView instagram, tickets;
    private SearchView searchView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        instagram = view.findViewById(R.id.iv_ig);
        tickets = view.findViewById(R.id.iv_tickets);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressbar);

        driversRecyclerView = view.findViewById(R.id.rv_drivers);
        driversRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        driverList = new ArrayList<>();
        driversAdapter = new DriversAdapter(getContext(), driverList);
        driversRecyclerView.setAdapter(driversAdapter);

        racesRecyclerView = view.findViewById(R.id.rv_races);
        racesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        raceList = new ArrayList<>();
        raceAdapter = new RaceAdapter(getContext(), raceList);
        racesRecyclerView.setAdapter(raceAdapter);

        loadDrivers();
        loadRaces();

        instagram.setOnClickListener(v -> openInstagram());
        tickets.setOnClickListener(v -> openTicketWebsite());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                driversAdapter.filter(query);
                raceAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                driversAdapter.filter(newText);
                raceAdapter.filter(newText);
                return false;
            }
        });

        return view;
    }

    private void loadDrivers() {
        ApiConfig.getApiService().getDrivers().enqueue(new Callback<DriverResponse>() {
            @Override
            public void onResponse(@NonNull Call<DriverResponse> call, @NonNull Response<DriverResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    driverList.clear();
                    driverList.addAll(response.body().getMRData().getDriverTable().getDrivers());
                    driversAdapter.notifyDataSetChanged();
                    driversAdapter.filter("");
                } else {
                    Log.e("HomeFragment", "Failed to fetch drivers data: " + response.message());
                    Toast.makeText(getContext(), "Failed to get drivers data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DriverResponse> call, @NonNull Throwable t) {
                Log.e("HomeFragment", "Error fetching drivers data", t);
                Toast.makeText(getContext(), "Error fetching drivers data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRaces() {
        ApiConfig.getApiService().getRaces().enqueue(new Callback<RaceResponse>() {
            @Override
            public void onResponse(@NonNull Call<RaceResponse> call, @NonNull Response<RaceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    raceList.clear();
                    raceList.addAll(response.body().getMRData().getRaceTable().getRaces());
                    raceAdapter.notifyDataSetChanged();
                    raceAdapter.filter("");
                } else {
                    Log.e("HomeFragment", "Failed to fetch races data: " + response.message());
                    Toast.makeText(getContext(), "Failed to get races data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RaceResponse> call, @NonNull Throwable t) {
                Log.e("HomeFragment", "Error fetching races data", t);
                Toast.makeText(getContext(), "Error fetching races data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void openInstagram() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/f1?igsh=MWx2Zm9mYm9ycWQ3eA=="));
        startActivity(intent);
    }

    private void openTicketWebsite() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tickets.formula1.com/en"));
        startActivity(intent1);
    }
}