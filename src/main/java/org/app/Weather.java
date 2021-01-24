package org.app;

public class Weather {

    private double temp;
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Weather(String cityName, double temp) {
        this.cityName = cityName;
        this.temp = temp;
    }

}
