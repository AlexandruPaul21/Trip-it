package com.alexsirbu.tripit.network;

import java.util.List;

public interface OnGetWeatherCallback {

    //happy path
    void onSuccess(List<WeatherWrapper> weatherWrappers);

    //unhappy path
    void onError();

}
