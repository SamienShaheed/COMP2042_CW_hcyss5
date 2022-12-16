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

/**
 * Controller class for all fxml files
 * @author Samien Shaheed-modified
 */
public class Controller extends Main {
    /**
     * Variable to store the dimensions of the grid
     * @author Samien Shaheed
     */
    private int gridSize;

    /**
     * TextField variable to store user input
     * @author Samien Shaheed
     */
    @FXML
    private TextField userName;
    /**
     * Text variable to display validation message
     * @author Samien Shaheed
     */
    @FXML
    private Text errorMsg;
    /**
     * Button variable to check if game started in dark mode
     * @author Samien Shaheed
     */
    @FXML
    private Button startButton_dark;
    /**
     * Button variable to check if 4x4 grid was selected
     * @author Samien Shaheed
     */
    @FXML
    private Button light_4x4;
    /**
     * Button variable to check if 5x5 grid was selected
     * @author Samien Shaheed
     */
    @FXML
    private Button light_5x5;
    /**
     * Button variable to check if 6x6 grid was selected
     * @author Samien Shaheed
     */
    @FXML
    private Button light_6x6;

    /**
     * Method to call Level Selection Screen
     * @author Samien Shaheed
     */
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

    /**
     * Method to change theme to Light Mode
     * @author Samien Shaheed
     */
    @FXML
    void lightMode(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        showMainMenuLight(currentStage);
    }

    /**
     * Method to change theme to Dark Mode
     * @author Samien Shaheed
     */
    @FXML
    void darkMode(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        showMainMenuDark(currentStage);
    }

    /**
     * Method to Quit Game
     * @author Samien Shaheed
     */
    @FXML
    void quitGame(MouseEvent event) { // Method to quit game when button pressed
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Method to start 4x4 game mode
     * @author Samien Shaheed
     */
    @FXML
    public void classicGameMode(ActionEvent event) throws IOException { // Method to handle start button in Main Menu
        boolean darkMode = event.getSource() == light_4x4;
        gridSize = 4;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }

    /**
     * Method to start 5x5 game mode
     * @author Samien Shaheed
     */
    @FXML
    public void largeGameMode(ActionEvent event) {
        boolean darkMode = event.getSource() == light_5x5;
        gridSize = 5;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }

    /**
     * Method to start 6x6 game mode
     * @author Samien Shaheed
     */
    @FXML
    public void wideGameMode(ActionEvent event) {
        boolean darkMode = event.getSource() == light_6x6;
        gridSize = 6;
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // get the current stage
        startGame(currentStage, gridSize,!darkMode); // pass currentStage to startGame function in Main.java
    }


}
