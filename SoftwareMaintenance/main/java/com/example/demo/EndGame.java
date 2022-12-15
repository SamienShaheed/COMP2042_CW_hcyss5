package com.example.demo;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class EndGame extends Main{
    private static EndGame singleInstance = null;
    /**
     * Array to store scores read from external file
     */
    private final int [] topScores = new int[10];
    /**
     * Variable to store number of scores to display on screen
     */
    private final int noOfTopScores = 5;

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    /**
     * Method to save score to external file
     * @param score variable to store score
     */
    private void saveScore(long score) {
        // Try Catch Block to store scores in a Txt File
        try {
            FileWriter leaderBoardUpdate = new FileWriter("Leaderboard.txt", true); // Create leaderBoardUpdate object
            leaderBoardUpdate.append(Long.toString(score)).append("\n"); // append the current instance's score to the txt file
            leaderBoardUpdate.close();
        } catch (IOException fileErr) { // Catch any errors found
            System.out.println("Could not update LeaderBoard");
            fileErr.printStackTrace();
        }
    }

    /**
     * Method to update external file with sorted scores
     * @param scores variable to store score
     */
    private void updateExternalFile(int scores[]) {
        try {
            FileWriter leaderBoardUpdate = new FileWriter("Leaderboard.txt");
            for(int i = 0; i < noOfTopScores; i++) { // Store array values into file
                leaderBoardUpdate.append(Integer.toString(scores[i])).append("\n"); 
            }
            leaderBoardUpdate.close();
        } catch (IOException fileErr) {
            System.out.println("Could not update LeaderBoard");
            fileErr.printStackTrace();
        }
    }

    /**
     * Method to read and sort scores into descending order
     */
    private void getTopScores() {
        // Try Catch Block to retrieve scores from external text file
        try {
            File leaderBoard = new File("Leaderboard.txt");
            Scanner fileReader = new Scanner(leaderBoard);
            int i = 0;
            while(fileReader.hasNextInt() && i < 10) {
                topScores[i++] = fileReader.nextInt(); // Store values into array
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Insertion sort to get top scores
        // Reference: https://www.geeksforgeeks.org/insertion-sort/
        for(int i=0; i < 10; i++) {
            int key = topScores[i];
            int j = i - 1;
            while(j >= 0 && topScores[j] < key) {
                topScores[j+1] = topScores[j];
                j--;
            }
            topScores[j+1] = key;
        }

        // Update the external file after sort
        updateExternalFile(topScores);

        // Debugging purposes
        for(int i = 0; i < noOfTopScores ; i++) {
            System.out.print(topScores[i] + " ");
        }
    }

    /**
     * Driving method to run end game screen
     * @param primaryStage main stage used for the game
     * @param score variable to store score
     * @param username variable to store username
     * @param darkMode boolean to check if dark mode is enabled
     */
    public void endGameShow(Group root, Stage primaryStage, long score, String username, boolean darkMode) {
         
        // Call function to save score to external file
        saveScore(score);
        getTopScores();

        // --------------------------------------Text------------------------------------
        // Generate Text to Display on screen
        Text text = new Text("Game Over");
        text.relocate(236, 65);
        text.setFont(Font.font("Rockwell", 80));
        root.getChildren().add(text);
        
        Text scoreText = new Text("Score");
        scoreText.relocate(200, 260);
        scoreText.setFont(Font.font("Rockwell", 60));
        root.getChildren().add(scoreText);
        
        Text currentScore = new Text(score + "");
        currentScore.relocate(200, 310);
        currentScore.setFont(Font.font("Rockwell", 50));
        root.getChildren().add(currentScore);
        
        if(darkMode) {
            text.setFill(Color.WHITE);
            currentScore.setFill(Color.WHITE);
            scoreText.setFill(Color.WHITE);
        } else {
            text.setFill(Color.rgb(119, 110, 101));
            currentScore.setFill(Color.rgb(119, 110, 101));
            scoreText.setFill(Color.rgb(119, 110, 101));
        }
        // ------------------------------------------------------------------------------ 
        
        // -------------------------------Leaderboard------------------------------------
        Text leaderBoardText = new Text("Top Scores");
        leaderBoardText.relocate(500, 140);
        leaderBoardText.setFont(Font.font("Rockwell",  50));
        root.getChildren().add(leaderBoardText);
        if(darkMode) {
            leaderBoardText.setFill(Color.WHITE);
        } else {
            leaderBoardText.setFill(Color.rgb(119, 110, 101));
        }
        
        int leaderBoardYPos = 220;

        for(int i=0 ; i < noOfTopScores ; i++) {
            Text leaderBoard = new Text((i+1) + ". " + Integer.toString(topScores[i]));
            leaderBoard.relocate(590, leaderBoardYPos);
            leaderBoard.setFont(Font.font("Rockwell", 40));
            root.getChildren().add(leaderBoard);

            if(darkMode) {
                if(score == topScores[i]) {
                    leaderBoard.setFill(Color.rgb(237, 207, 114));
                } else {
                    leaderBoard.setFill(Color.WHITE);
                }
            } else {
                if(score == topScores[i]) {
                    leaderBoard.setFill(Color.rgb(242, 177, 121));
                } else {
                    leaderBoard.setFill(Color.rgb(119, 110, 101));
                }
            }

            leaderBoardYPos += 80;
        }

        // ------------------------------------------------------------------------------


        // --------------------------------------Buttons---------------------------------
        // Generate Buttons to Display on Screen
        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(142, 432);

        quitButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Dialog");
            alert.setHeaderText("Quit from this page");
            alert.setContentText("Are you sure?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                primaryStage.close(); // Fixed bug where quit button should close window
            }
        });

        Button playAgain = new Button("Play Again");
        playAgain.setPrefSize(100,30);
        playAgain.setTextFill(Color.rgb(119, 110, 101));
        root.getChildren().add(playAgain);
        playAgain.relocate(326, 432);
        
        playAgain.setOnMouseClicked(event -> {
            try {
                if(darkMode) {
                    showMainMenuDark(primaryStage);
                } else {
                    showMainMenuLight(primaryStage);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // ------------------------------------------------------------------------------

    }
}
