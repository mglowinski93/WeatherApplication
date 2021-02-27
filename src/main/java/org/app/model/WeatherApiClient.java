package org.app.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherApiClient {

    static final ObjectMapper objectMapper = new ObjectMapper();
    private final static int minForecastDays = 0;
    private final static int maxForecastDays = 7;
    private final String apiKey;

    public WeatherApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public Map<Integer, DailyForecastData> getWeatherTempForecast(String cityName, int forecastDays) throws IOException, InvalidForecastDays {
        if (forecastDays < minForecastDays || forecastDays > maxForecastDays) {
            throw new InvalidForecastDays(forecastDays + " is invalid number for forecast days. Please select number " +
                    "between " + minForecastDays + " and " + maxForecastDays);
        }

        JsonNode weaterJsonData = requestWeatherData(cityName);

        return prepareWeatherData(weaterJsonData, forecastDays);
    }

    private JsonNode requestWeatherData(String cityName) throws IOException {
        JsonNode jsonData =
                objectMapper.readTree(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                        "&appid=" + apiKey + "&lang=en&units=metric"));
        String lat = jsonData.get("coord").get("lat").toString();
        String lon = jsonData.get("coord").get("lon").toString();

        return objectMapper.readTree(new URL("https://api.openweathermap.org/data/2" +
                ".5/onecall?lat=" + lat +
                "&lon=" + lon + "&exclude=minutely,hourly&appid=" + apiKey + "&units=metric"));

    }

    private Map<Integer, DailyForecastData> prepareWeatherData(JsonNode weaterJsonData, int forecastDays) {
        Map<Integer, DailyForecastData> weatherTempForecast = new LinkedHashMap<>();

        for (int i = 0; i < forecastDays; i++) {
            DailyForecastData dailyForecastData = new DailyForecastData(
                    weaterJsonData.get("daily").get(i).get("temp").get("day").asText(),
                    weaterJsonData.get("daily").get(i).get("weather").get(0).get("icon").asText()
            );

            weatherTempForecast.put(Integer.parseInt(weaterJsonData.get("daily").get(i).get("dt").asText()), dailyForecastData);
        }

        return weatherTempForecast;
    }

    public class InvalidForecastDays extends Exception {

        public InvalidForecastDays(String message) {
            super(message);
        }

    }

}
