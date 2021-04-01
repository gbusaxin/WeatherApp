package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather?appid=2b7c4afddfb27cd6f29f10c10b220f72&units=metric")
    Call<OpenWeatherMap> getWeatherByLocation(@Query("lat") double lat,@Query("lon") double lon);

    @GET("weather?appid=2b7c4afddfb27cd6f29f10c10b220f72&units=metric")
    Call<OpenWeatherMap> getWeatherByCityName(@Query("q")String name);
}
