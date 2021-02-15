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

        JsonNode jsonData =
                new ObjectMapper().readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + this.cityName +
                        "&appid=" + PropertiesReader.getProperty("OPEN_WEATHER_API_KEY") + "&lang=en&units=metric"));
        this.lat = jsonData.get("coord").get("lat").toString();
        this.lon = jsonData.get("coord").get("lon").toString();
    }

    public Map<Integer, String[]> getWeatherTempForecast(int forecastDays) throws IOException {
        if (forecastDays > 7) {
            forecastDays = 7;
        } else if (forecastDays < 0) {
            forecastDays = 0;
        }

        JsonNode weaterJsonData = new ObjectMapper().readTree(new URL("https://api.openweathermap.org/data/2" +
                ".5/onecall?lat=" + this.lat +
                "&lon=" + this.lon + "&exclude=minutely,hourly&appid=" + PropertiesReader.getProperty("OPEN_WEATHER_API_KEY") + "&units=metric"));

        Map<Integer, String[]> weatherTempForecast = new HashMap<>();

        for (int i = 0; i < forecastDays; i++) {
            String[] data = new String[]{weaterJsonData.get("daily").get(i).get("temp").get("day").asText(),
                    weaterJsonData.get("daily").get(i).get("weather").get(0).get("icon").asText()};

            weatherTempForecast.put(Integer.parseInt(weaterJsonData.get("daily").get(i).get("dt").asText()), data);
        }

        return weatherTempForecast;
    }

}
