package org.app.controller;

import org.app.ViewFactory;
import org.app.WeatherManager;

public abstract class BaseController {

    private WeatherManager weatherManager;
    private ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        this.weatherManager = weatherManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

}
