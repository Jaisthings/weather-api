package com.jaisthings.weather.services;

import com.jaisthings.weather.http.client.OpenWeatherApi;
import com.jaisthings.weather.models.CityWeather;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WeatherServiceTest {

    @InjectMocks
    WeatherService weatherService;
    @Mock
    OpenWeatherApi openWeatherApi;
    @Mock
    Call<CityWeather> call;

    @Test
    public void testWeatherReturnsCityWeatherObject() throws IOException {
        //given
        when(openWeatherApi.getCityWeather(anyString(), any(), any())).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(new CityWeather()));

        //when
        CityWeather cityWeather = weatherService.weather("paris");

        //then
        assertThat(cityWeather).isNotEqualTo(null);
    }
}
