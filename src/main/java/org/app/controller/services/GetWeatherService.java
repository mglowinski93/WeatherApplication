package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.Weather;
import java.io.IOException;

import java.util.Map;


public class GetWeatherService extends Service<Map<Integer, String>> {

    final String cityName;
    final int forcastDays;

    public GetWeatherService(String cityName, int forcastDays) {
        this.cityName = cityName;
        this.forcastDays = forcastDays;
    }

    private Map<Integer, String> getWeatherData() throws IOException {

        return new Weather(cityName).getWeatherTempForcast(this.forcastDays);

    }

    @Override
    protected Task<Map<Integer, String>> createTask() {
        return new Task<>() {
            @Override
            protected Map<Integer, String> call() throws Exception {
                return getWeatherData();
            }
        };
    }
}
