package org.app.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.PropertiesReader;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Weather {

    final String cityName;
    final String lat;
    final String lon;

    public Weather(String cityName) throws IOException {
        this.cityName = cityName;

        JsonNode jsonData;
        jsonData = new ObjectMapper().readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + this.cityName +
                "&appid=" + PropertiesReader.getProperty("OPEN_WEATHER_API_KEY") + "&lang=en&units=metric"));
        this.lat = jsonData.get("coord").get("lat").toString();
        this.lon = jsonData.get("coord").get("lon").toString();
    }

    public String getCityName() {
        return cityName;
    }

    public Map<Integer, String> getWeatherTempForcast(int forcastDays) throws IOException {
        if (forcastDays > 7) forcastDays = 7;
        else if (forcastDays < 0) forcastDays = 0;

        JsonNode weaterJsonData;
        weaterJsonData =
                new ObjectMapper().readTree(new URL("https://api.openweathermap.org/data/2.5/onecall?lat="+ this.lat +
                "&lon="+ this.lon +"&exclude=minutely,hourly&appid=" + PropertiesReader.getProperty("OPEN_WEATHER_API_KEY") + "&units=metric"));

        Map<Integer, String> weatherTempForcast = new HashMap<>();

        for(int i = 0; i < forcastDays; i++)
        {
            weatherTempForcast.put(Integer.parseInt(weaterJsonData.get("daily").get(i).get("dt").asText()),
                    weaterJsonData.get("daily").get(i).get("temp").get("day").asText());
        }

       return weatherTempForcast;
    }

}
