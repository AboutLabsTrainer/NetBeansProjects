package de.ibb.fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Erstes JavaFX-Fenster: ein Label und ein Button, der den Text ändert.
 */
public class HalloJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Label erstellen
        Label label = new Label("Hallo JavaFX!");
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: #66BB6A;");

        // Button
        Button button = new Button("Klick mich!");
        button.setOnAction(e -> label.setText("Geklickt!"));

        // Layout
        VBox root = new VBox(20, label, button);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1a1a2e; -fx-padding: 40;");

        // Scene und Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Hallo JavaFX!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Startet JavaFX Application Thread
    }
}

