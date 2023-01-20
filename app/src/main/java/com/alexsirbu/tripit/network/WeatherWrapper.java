package com.alexsirbu.tripit.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherWrapper {

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    //    @SerializedName("coord")
//    public Coord coord;
//
//    public class Coord {
//        @SerializedName("lon")
//        public Float lon;
//        @SerializedName("lat")
//        public Float lat;
//    }
//
//    @SerializedName("weather")
//    private List<Weather> weather;
//
//    public class Weather {
//        @SerializedName("id")
//        public Integer id;
//
//        @SerializedName("main")
//        public String main;
//
//        @SerializedName("description")
//        public String description;
//    }
//
//    @SerializedName("main")
//    private Main main;
//
//    public class Main {
//        @SerializedName("temp")
//        public Float temp;
//
//        @SerializedName("feels_like")
//        public Float realTemp;
//    }
//
//    public Coord getCoord() {
//        return coord;
//    }
//
//    public void setCoord(Coord coord) {
//        this.coord = coord;
//    }
//
//    public List<Weather> getWeather() {
//        return weather;
//    }
//
//    public void setWeather(List<Weather> weather) {
//        this.weather = weather;
//    }
//
//    public Main getMain() {
//        return main;
//    }
//
//    public void setMain(Main main) {
//        this.main = main;
//    }

}
