package com.alexsirbu.tripit.network;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static final String WEATHER_BASE_URL = "https://api.open-meteo.com/v1/";
    private static WeatherRepository weatherRepository;
    private WeatherApi weatherApi;

    private WeatherRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public static WeatherRepository getInstance() {
        if (weatherRepository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherRepository = new WeatherRepository(retrofit.create(WeatherApi.class));
        }
        return weatherRepository;
    }

    public void getWeather(final OnGetWeatherCallback callback) {
        weatherApi.getWeatherForLocation()
                .enqueue(new Callback<List<WeatherWrapper>>() {
                    @Override
                    public void onResponse(Call<List<WeatherWrapper>> call, Response<List<WeatherWrapper>> response) {
                        if (response.isSuccessful()) {
                            List<WeatherWrapper> weather = response.body();
                            Log.e("API", "here1");
                            if (weather != null) {
                                callback.onSuccess(weather);
                            } else {
                                Log.e("API", "here2");
                                callback.onError();
                            }
                        } else {
                            Log.e("API", "here3");
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<WeatherWrapper>> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
