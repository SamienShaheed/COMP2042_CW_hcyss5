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
    /**
     * Stores width of the game screen
     */
    static final int WIDTH = 900;
    /**
     * Stores height of the game screen
     */
    static final int HEIGHT = 600;
    /**
     * main stage used for the game
     */
    public Stage primaryStage;
    /**
     * Stores username input by player
     */
    private String username;

    /**
     * Stores Background Color for Light Mode
     */
    private static Color backgroundColor_LIGHT = Color.rgb(238, 228, 218, 0.73);
    /**
     * Stores Background Color for Dark Mode
     */
    private static Color backgroundColor_DARK = Color.rgb(64, 64, 64);

    /**
     * returns width of the Stage
     * @return returns width of the Stage
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * returns height of the stage
     * @return returns height of the stage
     */
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("2048"); // Set title of the program to 2048

        showMainMenuDark(primaryStage); // Call method to display Main Menu when program is run

    }

    /**
     * Method to display Main Menu Dark Theme fxml file
     * @param primaryStage main stage used for the game
     * @throws IOException
     */
    public void showMainMenuDark(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu_dark.fxml"));
        AnchorPane mainMenuDark = loader.load();
        Scene menuDark = new Scene(mainMenuDark);
        primaryStage.setScene(menuDark);
        primaryStage.show();
    }

    /**
     * Method to display Main Menu Light Theme fxml file
     * @param primaryStage main stage used for the game
     * @throws IOException
     */
    public void showMainMenuLight(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu_light.fxml"));
        AnchorPane mainMenuLight = loader.load();
        Scene menuLight = new Scene(mainMenuLight);
        primaryStage.setScene(menuLight);
        primaryStage.show();
    }

    /**
     * Method to display the different levels Dark Mode
     * @param primaryStage main stage used for the game
     * @param username variable to store the username input by player
     * @throws IOException
     */
    public void chooseLevelDark(Stage primaryStage, String username) throws IOException {
        this.username = username; // Store entered username

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChooseLevels_dark.fxml"));
        AnchorPane levels = loader.load();
        Scene level = new Scene(levels);
        primaryStage.setScene(level);
        primaryStage.show();
    }

    /**
     * Method to display the different levels Light Mode
     * @param primaryStage main stage used for the game
     * @param username variable to store the username input by player
     * @throws IOException
     */
    public void chooseLevelLight(Stage primaryStage, String username) throws IOException {
        this.username = username; // Store entered username

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ChooseLevels_light.fxml"));
        AnchorPane levels = loader.load();
        Scene level = new Scene(levels);
        primaryStage.setScene(level);
        primaryStage.show();
    }

    /**
     * Method to start the main game
     * @param primaryStage main stage used for the game
     * @param gridSize variable to store the level chosen
     * @param darkMode boolean to check if dark mode is enabled
     */
    public void startGame(Stage primaryStage, int gridSize, boolean darkMode) {
        if(darkMode) {
            Group gameRoot = new Group();
            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, backgroundColor_DARK);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, backgroundColor_DARK);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot, username, darkMode, gridSize);
        } else {
            System.out.print(username);
            Group gameRoot = new Group();
            Group endgameRoot = new Group();
            Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, backgroundColor_LIGHT);
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, backgroundColor_LIGHT);
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene();
            game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot, username, darkMode, gridSize);
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
