package com.jaisthings.weather.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data @ToString
public class CityWeather {
    Coord coord;
    List<Weather> weather;
    String base;
    int visibility;
    Wind wind;
    Cloud clouds;
    long dt;
    int id;
    String name;
    int cod;
    System sys;
    Main main;
}

@Data @ToString
class Coord{
    double lat;
    double lon;
}

@Data @ToString
class Weather{
    int id;
    String main;
    String description;
    String icon;
}

@Data @ToString
class Main{
    float temp;
    int pressure;
    int humidity;
    @SerializedName(value = "tmp_min")
    float tmpMin;
    @SerializedName(value="tmp_max")
    float tmpMax;
}

@Data @ToString
class Wind{
    float speed;
    int degree;
}

@Data @ToString
class Cloud{
    int all;
}

@Data @ToString
class System{
    int type;
    int id;
    double message;
    String country;
    long sunrise;
    long sunset;
}

