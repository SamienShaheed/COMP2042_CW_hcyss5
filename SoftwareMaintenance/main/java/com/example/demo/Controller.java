package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller extends Main {
    @FXML
    public void handlestartbtn(ActionEvent event) { // Method to handle start button in Main Menu
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage); // pass currentStage to startGame function in Main.java
    }
}
