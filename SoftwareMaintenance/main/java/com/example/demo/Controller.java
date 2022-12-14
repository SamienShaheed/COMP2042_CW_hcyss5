package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class Controller extends Main {
    private int gridSize;

    @FXML
    private TextField userName;
    @FXML
    private Text errorMsg;
    @FXML
    private Button button_Dark;
    @FXML
    private Button button_Light;
    @FXML
    private Button startButton_dark;
    @FXML
    private Button light_4x4;
    @FXML
    private Button light_5x5;
    @FXML
    private Button light_6x6;
    
    @FXML
    void chooseLevelScreen(MouseEvent event) throws IOException {
        
        if(event.getSource() == startButton_dark) {
            if(!userName.getText().equals("")) { // Checks if username was entered
                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                chooseLevelDark(currentStage, userName.getText()); // Call next Scene
            } else {
                // Sends error msg if no username
                errorMsg.setText("Please Enter Username!");
            }
        } else {
            if(!userName.getText().equals("")) { // Checks if username was entered
                Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                chooseLevelLight(currentStage, userName.getText()); // Call next Scene
            } else {
                // Sends error msg if no username
                errorMsg.setText("Please Enter Username!");
            }
        }
        
    }
    
    @FXML
    void lightMode(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        showMainMenuLight(currentStage);
    }

    @FXML
    void darkMode(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        showMainMenuDark(currentStage);
    }

    @FXML
    void quitGame(MouseEvent event) { // Method to quit game when button pressed
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void classicGameMode(ActionEvent event) throws IOException { // Method to handle start button in Main Menu
        boolean darkMode = event.getSource() == light_4x4;
        gridSize = 4;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }

    @FXML
    public void largeGameMode(ActionEvent event) {
        boolean darkMode = event.getSource() == light_5x5;
        gridSize = 5;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }

    @FXML
    public void wideGameMode(ActionEvent event) {
        boolean darkMode = event.getSource() == light_6x6;
        gridSize = 6;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }


}
