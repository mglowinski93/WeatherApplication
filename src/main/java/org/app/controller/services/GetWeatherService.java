package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.DailyForecastData;
import org.app.model.WeatherApiClient;

import java.io.IOException;
import java.util.Map;


public class GetWeatherService extends Service<Map<Integer, DailyForecastData>> {

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

    private Map<Integer, DailyForecastData> getWeatherData() throws IOException, WeatherApiClient.InvalidForecastDays {

        return weatherApiClient.getWeatherTempForecast(cityName, forecastDays);

    }

    @Override
    protected Task<Map<Integer, DailyForecastData>> createTask() {
        return new Task<>() {
            @Override
            protected Map<Integer, DailyForecastData> call() throws Exception {
                return getWeatherData();
            }
        };
    }
}
