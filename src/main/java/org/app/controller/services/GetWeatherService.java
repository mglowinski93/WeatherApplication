package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.WeatherApiClient;

import java.io.IOException;
import java.util.Map;


public class GetWeatherService extends Service<Map<Integer, String[]>> {

    private final WeatherApiClient weatherApiClient;
    private final String apiKey;
    private String cityName;
    private int forecastDays;

    public GetWeatherService(String apiKey) {
        this.apiKey = apiKey;
        weatherApiClient = new WeatherApiClient(this.apiKey);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getForecastDays() {
        return forecastDays;
    }

    public void setForecastDays(int forecastDays) {
        this.forecastDays = forecastDays;
    }

    private Map<Integer, String[]> getWeatherData() throws IOException, WeatherApiClient.InvalidForecastDays {

        return weatherApiClient.getWeatherTempForecast(cityName, forecastDays);

    }

    @Override
    protected Task<Map<Integer, String[]>> createTask() {
        return new Task<>() {
            @Override
            protected Map<Integer, String[]> call() throws Exception {
                return getWeatherData();
            }
        };
    }
}
