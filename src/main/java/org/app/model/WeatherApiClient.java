package org.app.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherApiClient {

    static final ObjectMapper objectMapper = new ObjectMapper();
    private final String apiKey;

    public WeatherApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public Map<Integer, String[]> getWeatherTempForecast(String cityName, int forecastDays) throws IOException {
        if (forecastDays > 7) {
            forecastDays = 7;
        } else if (forecastDays < 0) {
            forecastDays = 0;
        }
        JsonNode jsonData =
                objectMapper.readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                        "&appid=" + apiKey + "&lang=en&units=metric"));
        String lat = jsonData.get("coord").get("lat").toString();
        String lon = jsonData.get("coord").get("lon").toString();

        JsonNode weaterJsonData = objectMapper.readTree(new URL("https://api.openweathermap.org/data/2" +
                ".5/onecall?lat=" + lat +
                "&lon=" + lon + "&exclude=minutely,hourly&appid=" + apiKey + "&units=metric"));

        Map<Integer, String[]> weatherTempForecast = new HashMap<>();

        for (int i = 0; i < forecastDays; i++) {
            String[] data = new String[]{weaterJsonData.get("daily").get(i).get("temp").get("day").asText(),
                    weaterJsonData.get("daily").get(i).get("weather").get(0).get("icon").asText()};

            weatherTempForecast.put(Integer.parseInt(weaterJsonData.get("daily").get(i).get("dt").asText()), data);
        }

        return weatherTempForecast;
    }

}
