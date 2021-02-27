package org.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherApiClientTest {

    WeatherApiClient weatherApiClient = new WeatherApiClient("123");

    @Test()
    void ExceptionRaisedWhenInvalidForecastDays() {

        Assertions.assertThrows(WeatherApiClient.InvalidForecastDays.class,
                () -> weatherApiClient.getWeatherTempForecast("London", 8));

    }

}
