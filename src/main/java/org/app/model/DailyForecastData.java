package org.app.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyForecastData that = (DailyForecastData) o;
        return temp.equals(that.temp) && icon.equals(that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, icon);
    }

}
