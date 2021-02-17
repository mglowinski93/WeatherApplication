package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.Weather;

import java.io.IOException;
import java.util.Map;


public class GetWeatherService extends Service<Map<Integer, String[]>> {

    private String cityName;
    private int forecastDays;

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

    private Map<Integer, String[]> getWeatherData() throws IOException {

        return new Weather(cityName).getWeatherTempForecast(forecastDays);

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
