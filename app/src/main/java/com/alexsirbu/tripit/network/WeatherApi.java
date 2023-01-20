package com.alexsirbu.tripit.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("/forecast?latitude=46.77&longitude=23.62&hourly=temperature_2m")
    Call<List<WeatherWrapper>> getWeatherForLocation();
}
