package com.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
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

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        Text text = new Text("GAME OVER");
        text.setFill(Color.WHITE);
        text.relocate((screenWidth * 0.24),(screenHeight * 0.15));
        text.setFont(Font.font("Rockwell", 80));
        root.getChildren().add(text);

        Text scoreText = new Text(score+"");
        scoreText.setFill(Color.WHITE);
        scoreText.relocate((screenWidth * 0.4),(screenHeight * 0.5));
        scoreText.setFont(Font.font("Rockwell", 80));
        root.getChildren().add(scoreText);

        // Try Catch Block to store scores in a Txt File
        try {
            FileWriter leaderBoardUpdate = new FileWriter("Leaderboard.txt", true); // Create leaderBoardUpdate object
            leaderBoardUpdate.append(Long.toString(score)).append("\n"); // append the current instance's score to the txt file
            leaderBoardUpdate.close();
        } catch (IOException fileErr) { // Catch any errors found
            System.out.println("Could not update LeaderBoard");
            fileErr.printStackTrace();
        }


        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate((screenWidth * 0.4),(screenHeight * 0.6));

        quitButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Dialog");
            alert.setHeaderText("Quit from this page");
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                root.getChildren().clear();
            }
        });
    }
}
