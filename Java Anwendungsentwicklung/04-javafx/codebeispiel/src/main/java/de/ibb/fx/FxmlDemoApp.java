package de.ibb.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Startet die Oberfläche aus {@code view.fxml}, statt sie in Java zusammenzubauen.
 */
public class FxmlDemoApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/view.fxml")
        );

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("FXML Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

