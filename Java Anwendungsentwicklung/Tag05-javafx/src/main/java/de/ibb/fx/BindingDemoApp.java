package de.ibb.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Zeigt Property Binding: das Label folgt dem Slider ohne eigenen Listener.
 */
public class BindingDemoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Slider slider = new Slider(0, 100, 50);

        Label label = new Label();
        // Binding: Label zeigt IMMER den Slider-Wert
        label.textProperty().bind(
                slider.valueProperty().asString("Wert: %.0f")
        );

        VBox root = new VBox(20, label, slider);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Property Binding Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

