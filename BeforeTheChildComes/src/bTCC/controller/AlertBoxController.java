package bTCC.controller;

import bTCC.model.AlertBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlertBoxController {

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    void noButtonClick(ActionEvent event) {
    	AlertBox.close();
    }

    @FXML
    void yesButtonClick(ActionEvent event) {
    	Platform.exit();
    }
}
