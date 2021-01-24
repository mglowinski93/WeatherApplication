package org.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.controller.services.WeatherService;
import org.app.model.Weather;
import org.app.view.ViewFactory;

public class MainWindowController extends BaseController{

    @FXML
    private TextField fieldSearch;

    @FXML
    private Label labelInfo;

    @FXML
    public void onSearch(){
        WeatherService weatherService = new WeatherService();
        weatherService.setCityName(fieldSearch.getText());
        weatherService.start();
        weatherService.setOnSucceeded(event -> {
            Weather weather = weatherService.getValue();
            labelInfo.setText(weather.getCityName() + ": " + weather.getTemp());
        });

    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName){
        super(viewFactory, fxmlName);
    }
}
