package com.example.demo;

import javafx.application.Application;
//import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.ButtonType;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.control.Control;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
//import java.util.Optional;
import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    private static Scanner input = new Scanner(System.in);
    private Group gameRoot = new Group();
    private Scene gameScene;
    public Stage primaryStage;

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
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

        Group menuRoot = new Group();
        Group accountRoot = new Group();
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
        

        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(64, 64, 64));
        setGameScene(gameScene);
        primaryStage.setScene(gameScene);
        GameScene game = new GameScene();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
