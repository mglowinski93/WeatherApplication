package org.app.model;

public class DailyForecastData {
    private final String temp;
    private final String icon;

    public DailyForecastData(String temp, String icon) {
        this.temp = temp;
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getIcon() {
        return icon;
    }
}
