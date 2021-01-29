package org.app.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.app.controller.services.GetWeatherService;
import org.app.model.Date;
import org.app.view.ViewFactory;

import java.util.Iterator;
import java.util.Map;

public class MainWindowController extends BaseController{

    @FXML
    private TextField currentSearchField, destinationSearchField;

    @FXML
    private VBox currentDatesVBox, currentTempsVBox, destinationDatesVBox, destinationTempsVBox;

    @FXML
    public void currentLocationOnSearch(){

        this.startGetWeatherService(currentSearchField, currentDatesVBox, currentTempsVBox);
    }

    @FXML
    public void destinationLocationOnSearch(){

        this.startGetWeatherService(destinationSearchField, destinationDatesVBox, destinationTempsVBox);
    }

    private void clearLabelsInsideVBox(VBox vBox){

        for(Node child : vBox.getChildren()) {
            Label label = (Label) child;
            label.setText("");
        }

    }

    private void startGetWeatherService(TextField searchField, VBox datesVBox, VBox tempVBox){
        GetWeatherService weatherService = new GetWeatherService(searchField.getText(), datesVBox.getChildren().size());
        weatherService.restart();

        this.clearLabelsInsideVBox(datesVBox);
        this.clearLabelsInsideVBox(tempVBox);

        weatherService.setOnSucceeded(event -> {
            Map<Integer, String> weatherTempForcast = weatherService.getValue();
            Date date = new Date();

            int i = 0;
            Iterator<Map.Entry<Integer, String>> it = weatherTempForcast.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, String> pair = it.next();

                Label dateLabel = (Label)datesVBox.getChildren().get(i);
                dateLabel.setText(date.getDateFromUTC(pair.getKey()));

                Label tempLabel = (Label)tempVBox.getChildren().get(i);
                tempLabel.setText(pair.getValue() + " °C");
                i++;
            }
        });
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName){
        super(viewFactory, fxmlName);
    }
}
