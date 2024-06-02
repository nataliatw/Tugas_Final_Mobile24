package com.example.f1raceandcircuitinformation;

import com.example.f1raceandcircuitinformation.Response.Driver;
import com.example.f1raceandcircuitinformation.Response.DriverResponse;
import com.example.f1raceandcircuitinformation.Response.Race;
import com.example.f1raceandcircuitinformation.Response.RaceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("2024/drivers.json")
    Call<DriverResponse> getDrivers();

    @GET("current.json")
    Call<RaceResponse> getRaces();

    @GET("2024/drivers/{driverId}.json")
    Call<DriverResponse> getDriverDetail(@Path("driverId") String driverId);
}

