package org.app.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.app.model.Weather;

public class WeatherService extends Service<Weather> {

    private String cityName;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private Weather getWeatherData(){
        //Logic for returning Weather data
        return new Weather(cityName, 14.5);
    }

    @Override
    protected Task<Weather> createTask() {
        return new Task<Weather>() {
            @Override
            protected Weather call() throws Exception {
                return getWeatherData();
            }
        };
    }
}
