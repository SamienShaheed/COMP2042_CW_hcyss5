package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Controller extends Main {
    private int gridSize;

    @FXML
    public void classicGameMode(ActionEvent event) throws IOException { // Method to handle start button in Main Menu
        gridSize = 4;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize); // pass currentStage to startGame function in Main.java
    }

    @FXML
    public void largeGameMode(ActionEvent event) {
        gridSize = 5;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize); // pass currentStage to startGame function in Main.java
    }

    @FXML
    public void wideGameMode(ActionEvent event) {
        gridSize = 6;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize); // pass currentStage to startGame function in Main.java
    }
}
