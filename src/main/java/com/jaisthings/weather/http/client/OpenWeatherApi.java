package com.jaisthings.weather.http.client;

import com.jaisthings.weather.models.CityWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("weather")
    Call<CityWeather> getCityWeather(@Query("q") String city, @Query("appid") String apiToken, @Query("units") String units);
}
