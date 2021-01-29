package org.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    private VBox currentDataVBox, destinationDataVBox;

    @FXML
    public void currentLocationOnSearch(){

        this.startGetWeatherService(currentSearchField, currentDataVBox);
    }

    @FXML
    public void destinationLocationOnSearch(){

        this.startGetWeatherService(destinationSearchField, destinationDataVBox);
    }

    private void clearLabelsInsideVBox(VBox vBox){

        for(int i = 0; i < vBox.getChildren().size(); i++) {

            HBox dataHBox = (HBox) vBox.getChildren().get(i);
            ImageView imageView = (ImageView) dataHBox.getChildren().get(0);
            VBox labelVBox = (VBox) dataHBox.getChildren().get(1);
            Label dateLabel = (Label) labelVBox.getChildren().get(0);
            Label tempLabel = (Label) labelVBox.getChildren().get(1);

            imageView.setImage(null);
            dateLabel.setText("");
            tempLabel.setText("");

        }

    }

    private void startGetWeatherService(TextField searchField, VBox dataVBox){
        GetWeatherService weatherService = new GetWeatherService(searchField.getText(), dataVBox.getChildren().size());
        weatherService.restart();

        this.clearLabelsInsideVBox(dataVBox);

        weatherService.setOnSucceeded(event -> {
            Map<Integer, String[]> weatherTempForcast = weatherService.getValue();
            Date date = new Date();

            int i = 0;
            Iterator<Map.Entry<Integer, String[]>> it = weatherTempForcast.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, String[]> pair = it.next();

                HBox dataHBox = (HBox) dataVBox.getChildren().get(i);
                ImageView imageView = (ImageView) dataHBox.getChildren().get(0);

                VBox labelVBox = (VBox) dataHBox.getChildren().get(1);
                Label dateLabel = (Label) labelVBox.getChildren().get(0);
                Label tempLabel = (Label) labelVBox.getChildren().get(1);

                String imagePath = "images/" + pair.getValue()[1] + ".png";
                Image image = new Image(imagePath);
                imageView.setImage(image);


                dateLabel.setText(date.getDateFromUTC(pair.getKey()));
                tempLabel.setText(pair.getValue()[0] + " °C");

                i++;
            }
        });
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName){
        super(viewFactory, fxmlName);
    }
}
