package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndGame extends Main{
    private static EndGame singleInstance = null;

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

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

    public void endGameShow(Group root, Stage primaryStage, long score) {
        
        saveScore(score);

        Text text = new Text("Game Over");
        text.setFill(Color.WHITE);
        text.relocate(236, 65);
        text.setFont(Font.font("Rockwell", 80));
        root.getChildren().add(text);

        Text scoreText = new Text("Score");
        scoreText.setFill(Color.WHITE);
        scoreText.relocate(200, 260);
        scoreText.setFont(Font.font("Rockwell", 60));
        root.getChildren().add(scoreText);
        
        Text currentScore = new Text(score + "");
        currentScore.setFill(Color.WHITE);
        currentScore.relocate(200, 302);
        currentScore.setFont(Font.font("Rockwell", 50));
        root.getChildren().add(currentScore);

        // Button to quit game
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
        playAgain.setTextFill(Color.BLACK);
        root.getChildren().add(playAgain);
        playAgain.relocate(326, 432);

        playAgain.setOnMouseClicked(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
