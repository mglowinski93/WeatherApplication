package org.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.controller.services.GetWeatherService;
import org.app.model.Date;
import org.app.view.ViewFactory;

import java.util.Map;

public class MainWindowController extends BaseController{

    @FXML
    private TextField fieldSearch;

    @FXML
    private Label labelInfo;

    @FXML
    public void onSearch(){
        GetWeatherService weatherService = new GetWeatherService(fieldSearch.getText());
        weatherService.start();
        weatherService.setOnSucceeded(event -> {
            Map<Integer, String> weatherTempForcast = weatherService.getValue();
            Date date = new Date();

            for (Map.Entry<Integer, String> entry : weatherTempForcast.entrySet()) {
                System.out.println(date.getDateFromUTC(entry.getKey()) + "/" + entry.getValue());
            }

            //labelInfo.setText(date.getDateFromUTC(weatherTempForcast.keySet());
        });

    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName){
        super(viewFactory, fxmlName);
    }
}
