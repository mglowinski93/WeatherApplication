package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.Weather;
import java.io.IOException;

import java.util.Map;


public class GetWeatherService extends Service<Map<Integer, String>> {

    final String cityName;

    public GetWeatherService(String cityName) {
        this.cityName = cityName;
    }

    private Map<Integer, String> getWeatherData() throws IOException {

        return new Weather(cityName).getWeatherTempForcast(5);

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
