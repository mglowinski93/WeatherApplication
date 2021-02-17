package org.app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.app.PropertiesReader;
import org.app.controller.services.GetWeatherService;
import org.app.model.DailyForecastData;
import org.app.model.Date;
import org.app.view.ViewFactory;

import java.util.Map;

public class MainWindowController extends BaseController {

    private final GetWeatherService weatherService;
    private String openWeatherApiKey = null;
    @FXML
    private TextField currentSearchField, destinationSearchField;
    @FXML
    private VBox currentDataVBox, destinationDataVBox;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        try {
            openWeatherApiKey = PropertiesReader.getProperty("OPEN_WEATHER_API_KEY");
        } catch (ExceptionInInitializerError e) {
            closeApplicationWithErrorCode(PropertiesReader.PROP_FILE + " does not exist. Please, create it and restart " +
                    "application.");
        }

        if (openWeatherApiKey == null || openWeatherApiKey.isEmpty()) {
            closeApplicationWithErrorCode(PropertiesReader.PROP_FILE + " does not contain OPEN_WEATHER_API_KEY. " +
                    "Please, add it and restart application.");
        }

        weatherService = new GetWeatherService(openWeatherApiKey);
    }

    private void closeApplicationWithErrorCode(String message) {
        System.out.println(message);
        Platform.exit();
        System.exit(1);
    }

    @FXML
    public void currentLocationOnSearch() {

        startGetWeatherService(currentSearchField, currentDataVBox);
    }

    @FXML
    public void destinationLocationOnSearch() {

        startGetWeatherService(destinationSearchField, destinationDataVBox);
    }

    private HBox getHbox(VBox vBox, int hBoxNumber) {
        return (HBox) vBox.getChildren().get(hBoxNumber);
    }

    private VBox getLabelVbox(HBox hBox) {
        return (VBox) hBox.getChildren().get(1);
    }

    private Label getDataLabel(VBox vBox, int labelNumber) {
        HBox dataHBox = getHbox(vBox, labelNumber);
        VBox labelVBox = getLabelVbox(dataHBox);
        return (Label) labelVBox.getChildren().get(0);
    }

    private Label getTempLabel(VBox vBox, int labelNumber) {
        HBox dataHBox = getHbox(vBox, labelNumber);
        VBox labelVBox = getLabelVbox(dataHBox);
        return (Label) labelVBox.getChildren().get(1);
    }

    private ImageView getImageView(VBox vBox, int imageNumber) {
        HBox dataHBox = getHbox(vBox, imageNumber);
        return (ImageView) dataHBox.getChildren().get(0);
    }

    private void setImageView(ImageView imageView, String imageName) {
        String imagePath = "images/" + imageName + ".png";
        Image image = new Image(imagePath);
        imageView.setImage(image);

    }

    private void clearLabelsInsideVBox(VBox vBox) {

        for (int i = 0; i < weatherService.getForecastDays(); i++) {

            ImageView imageView = getImageView(vBox, i);
            Label dateLabel = getDataLabel(vBox, i);
            Label tempLabel = getTempLabel(vBox, i);

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
            Map<Integer, DailyForecastData> weatherTempForecast = weatherService.getValue();
            Date date = new Date();

            int i = 0;
            for (Map.Entry<Integer, DailyForecastData> pair : weatherTempForecast.entrySet()) {
                ImageView imageView = getImageView(dataVBox, i);
                Label dateLabel = getDataLabel(dataVBox, i);
                Label tempLabel = getTempLabel(dataVBox, i);

                setImageView(imageView, pair.getValue().getIcon());
                dateLabel.setText(date.getDateFromUTC(pair.getKey()));
                tempLabel.setText(pair.getValue().getTemp() + " °C");

                i++;
            }
        });

        weatherService.setOnFailed(event -> {
            clearLabelsInsideVBox(dataVBox);
            weatherService.getException().printStackTrace();


            ImageView imageView = getImageView(dataVBox, 0);
            Label dateLabel = getDataLabel(dataVBox, 0);

            setImageView(imageView, "warning_mark");
            dateLabel.setText("Failed to load data\nfor " + weatherService.getCityName());

        });
    }
}
