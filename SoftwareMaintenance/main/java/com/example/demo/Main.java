package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
// Removed unused imports

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 600;
    public Stage primaryStage;
    private String username;

    private static Color backgroundColor_LIGHT = Color.rgb(238, 228, 218, 0.73);
    private static Color backgroundColor_DARK = Color.rgb(64, 64, 64);

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

        showMainMenuDark(primaryStage); // Call method to display Main Menu when program is run

    }

    // Method to display Main Menu Dark Theme
    public void showMainMenuDark(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu_dark.fxml"));
        AnchorPane mainMenuDark = loader.load();
        Scene menuDark = new Scene(mainMenuDark);
        primaryStage.setScene(menuDark);
        primaryStage.show();
    }

    // Method to display Main Menu Light Theme
    public void showMainMenuLight(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu_light.fxml"));
        AnchorPane mainMenuLight = loader.load();
        Scene menuLight = new Scene(mainMenuLight);
        primaryStage.setScene(menuLight);
        primaryStage.show();
    }

    // Method to display the different levels Dark Mode
    public void chooseLevelDark(Stage primaryStage, String username) throws IOException {
        this.username = username; // Store entered username

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChooseLevels_dark.fxml"));
        AnchorPane levels = loader.load();
        Scene level = new Scene(levels);
        primaryStage.setScene(level);
        primaryStage.show();
    }
    
    // Method to display the different levels Light Mode
    public void chooseLevelLight(Stage primaryStage, String username) throws IOException {
        this.username = username; // Store entered username

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChooseLevels_light.fxml"));
        AnchorPane levels = loader.load();
        Scene level = new Scene(levels);
        primaryStage.setScene(level);
        primaryStage.show();
    }

    // Method to start game
    public void startGame(Stage primaryStage, int gridSize, boolean darkMode) {
        if(darkMode) {
            Group gameRoot = new Group();
            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, backgroundColor_DARK);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, backgroundColor_DARK);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            GameScene.setN(gridSize);
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot, username, darkMode);
        } else {
            Group gameRoot = new Group();
            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, backgroundColor_LIGHT);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, backgroundColor_LIGHT);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            GameScene.setN(gridSize);
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot, username, darkMode);
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
