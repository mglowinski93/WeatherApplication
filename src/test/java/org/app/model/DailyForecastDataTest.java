package org.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DailyForecastDataTest {

    @Test
    void dailyForecastDataStore() {

        String temp = "20.02";
        String icon = "04d";

        DailyForecastData dailyForecastDataTest = new DailyForecastData(temp, icon);

        Assertions.assertEquals(dailyForecastDataTest.getTemp(), temp);
        Assertions.assertEquals(dailyForecastDataTest.getIcon(), icon);

    }

}
