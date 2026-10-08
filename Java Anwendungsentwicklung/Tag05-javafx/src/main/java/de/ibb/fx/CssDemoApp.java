package de.ibb.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Lädt {@code styles.css} und zeigt, wie Style-Klassen das Aussehen der Controls steuern.
 */
public class CssDemoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("CSS Demo");
        title.getStyleClass().add("label-title");

        Button button1 = new Button("Primary");
        Button button2 = new Button("Secondary");
        Button button3 = new Button("Action");

        HBox buttonRow = new HBox(10, button1, button2, button3);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, title, buttonRow);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(
                getClass().getResource("/styles.css").toExternalForm()
        );

        primaryStage.setTitle("CSS Styling Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

