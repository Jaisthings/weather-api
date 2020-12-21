package com.jaisthings.weather.controllers;

import com.jaisthings.weather.models.CityWeather;
import com.jaisthings.weather.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @CrossOrigin
    @GetMapping("/weather/{city}")
    public ResponseEntity<CityWeather> getCityWeather(@PathVariable String city) {
        log.info("Fetching weather information  {} ", city);
        CityWeather weather = null;
        ResponseEntity<CityWeather> responseEntity;
        try {
            weather = weatherService.weather(city);
            responseEntity = new ResponseEntity<>(weather, HttpStatus.OK);
        } catch (IOException ie) {
            log.error("Exception while fetching weather information for {} - {}", city, ie.getMessage());
            responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @CrossOrigin
    @GetMapping("/health")
    public ResponseEntity<Object> health() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
