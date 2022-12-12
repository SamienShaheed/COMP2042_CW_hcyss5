package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
// Removed unused imports

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 600;
    public Stage primaryStage;

    public int getWidth() {
        return WIDTH;
    }
    
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("2048"); // Set title of the program to 2048

        showMainMenu(); // Call method to display Main Menu when program is run
    }

    // Method to display Main Menu
    private void showMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu.fxml"));
        AnchorPane mainMenu = loader.load();
        Scene menu = new Scene(mainMenu);
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    // Method to start game
    public void startGame(Stage primaryStage) {

        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(64, 64, 64));
        

        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(64, 64, 64));
        primaryStage.setScene(gameScene);
        GameScene game = new GameScene();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
