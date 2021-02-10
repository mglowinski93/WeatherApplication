package org.app.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.app.controller.BaseController;
import org.app.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {

    public void showMainWindow() throws IOException {
        BaseController controller = new MainWindowController(this, "../windows/MainWindow.fxml");
        this.initializeStage(controller);
    }

    private void initializeStage(BaseController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Weather App");
        stage.show();
    }

}
