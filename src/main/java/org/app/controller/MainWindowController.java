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

import java.util.Map;

public class MainWindowController extends BaseController {

    private final GetWeatherService weatherService = new GetWeatherService();
    @FXML
    private TextField currentSearchField, destinationSearchField;
    @FXML
    private VBox currentDataVBox, destinationDataVBox;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    public void currentLocationOnSearch() {

        startGetWeatherService(currentSearchField, currentDataVBox);
    }

    @FXML
    public void destinationLocationOnSearch() {

        startGetWeatherService(destinationSearchField, destinationDataVBox);
    }

    private void clearLabelsInsideVBox(VBox vBox) {

        for (int i = 0; i < vBox.getChildren().size(); i++) {

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

    private void startGetWeatherService(TextField searchField, VBox dataVBox) {
        weatherService.setCityName(searchField.getText());
        weatherService.setForecastDays(dataVBox.getChildren().size());
        weatherService.restart();

        clearLabelsInsideVBox(dataVBox);

        weatherService.setOnSucceeded(event -> {
            Map<Integer, String[]> weatherTempForecast = weatherService.getValue();
            Date date = new Date();

            int i = 0;
            for (Map.Entry<Integer, String[]> pair : weatherTempForecast.entrySet()) {
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

        weatherService.setOnFailed(event -> {
            clearLabelsInsideVBox(dataVBox);
            weatherService.getException().printStackTrace();
        });
    }
}
