package com.jaisthings.weather.controllers;

import com.jaisthings.weather.models.CityWeather;
import com.jaisthings.weather.services.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;

    @Mock
    WeatherService weatherService;

    @Test
    void testHealth() {
        //given
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        //when
        ResponseEntity<Object> responseEntity = weatherController.health();
        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testGetCityWeatherReturnsCityWeather() throws IOException {
        //given
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        CityWeather cityWeather = new CityWeather();
        when(weatherService.weather(anyString())).thenReturn(cityWeather);

        //when
        ResponseEntity<CityWeather> responseEntity = weatherController.getCityWeather("");

        //then
        assertThat(responseEntity.getBody()).isInstanceOf(CityWeather.class);
    }

    @Test
    void testGetCityWeatherReturnsNoDataOnException() throws IOException {
        //given
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        CityWeather cityWeather = new CityWeather();
        when(weatherService.weather(anyString())).thenThrow(IOException.class);

        //when
        ResponseEntity<CityWeather> responseEntity = weatherController.getCityWeather("");

        //then
        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);

    }
}
