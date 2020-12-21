package com.jaisthings.weather.services;

import com.jaisthings.weather.http.client.OpenWeatherApi;
import com.jaisthings.weather.models.CityWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Slf4j
public class WeatherService {
    @Value("${api.token:#{'environment.api.token'}}")
    private String authToken;
    @Value("${weather.endpoint:#{'environment.weather.endpoint'}}")
    private String baseEndpoint;

    private OpenWeatherApi openWeatherApi;

    public CityWeather weather(String city) throws IOException {
        Response<CityWeather> response = openWeatherApi.getCityWeather(city,authToken,"metric").execute();
        log.info("{}",response.body());
        return response.body();
    }

    @PostConstruct
    public void initializeDependencies(){
        log.info("####### Token {} Endpoint {} ",authToken,baseEndpoint);
        this.openWeatherApi = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseEndpoint).build().create(OpenWeatherApi.class);
    }
}
