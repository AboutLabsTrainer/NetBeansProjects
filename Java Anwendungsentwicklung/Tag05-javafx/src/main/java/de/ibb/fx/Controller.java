package de.ibb.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Reagiert auf den Button in der FXML-Datei und setzt den Text des Labels.
 */
public class Controller {

    @FXML
    private Label label;

    @FXML
    private void handleClick(ActionEvent event) {
        label.setText("Geklickt!");
    }
}

