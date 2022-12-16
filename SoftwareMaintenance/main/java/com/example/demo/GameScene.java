package com.example.demo;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Handles actual gameplay logic
 * @author Samien Shaheed-modified
 */
public class GameScene extends Main{
    /**
     * Stores length for playable grid
     * @author Samien Shaheed-modified
     */
    private static final int gridLength = 600;
    /**
     * Stores value for distance between each cell in the grid
     */
    private final static int distanceBetweenCells = 10;
    private final TextMaker textMaker = TextMaker.getSingleInstance();
    private final Cell[][] cells = new Cell[n][n];

    /**
     * Stores score
     */
    private long score = 0;
    /**
     * Stores dimensions of the grid
     */
    private static int n = 8;
    private static double LENGTH;
    
    private Group root;

    /**
     * Method to set grid size of the game
     * @param number integer to set the grid size
     */
    public static void setN(int number) {
        n = number;
        LENGTH = (gridLength - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * Method to return length of grid
     * @return returns length of grid
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * Method to randomly spawn a tile onto the grid
     */
    private void randomFillNumber() { // Removed unused int turn parameter

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }

        Text text;
        Random random = new Random();
        boolean putTwo = random.nextInt() % 2 != 0; // Simplified if command to set putTwo boolean value
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    /**
     * Method to check if empty cells exist
     * @return returns 1 if an empty cell exists, returns -1 if no empty cells
     */
    // Check if empty cells exist on the grid
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
            }
        }
        return -1;
    }

    /**
     * Method to calculate destination coordinates of a given cell
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @param direct variable to store direction of movement
     * @return returns destination coordinate of cell (i,j)
     * @author Samien Shaheed-modified
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate;

        switch (direct) { // Converted if else to switch case statement
            case 'l' -> {
                coordinate = j;
                for (int k = j - 1; k >= 0; k--) {
                    if (cells[i][k].getNumber() != 0) {
                        return k + 1;
                    } else if (k == 0) {
                        coordinate = 0;
                    }
                }
                return coordinate;
            }

            case 'r' -> {
                coordinate = j;
                for (int k = j + 1; k <= n - 1; k++) {
                    if (cells[i][k].getNumber() != 0) {
                        return k - 1; // Simplified code to return k - 1
                    } else if (k == n - 1) {
                        coordinate = n - 1;
                    }
                }
                return coordinate;
            }

            case 'd' -> {
                coordinate = i;
                for (int k = i + 1; k <= n - 1; k++) {
                    if (cells[k][j].getNumber() != 0) {
                        return k - 1;

                    } else if (k == n - 1) {
                        coordinate = n - 1;
                    }
                }
                return coordinate;
            }

            case 'u' -> {
                coordinate = i;
                for (int k = i - 1; k >= 0; k--) {
                    if (cells[k][j].getNumber() != 0) {
                        return k + 1;
                    } else if (k == 0) {
                        coordinate = 0;
                    }
                }
                return coordinate;
            }
        }
        return -1;
    }

    /**
     * Method to move all cells to the Left
     */
    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Method to move all cells to the right
     */
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Method to move every cell up
     */
    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        } 
    }

    /**
     * Method to move every cell down
     */
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Method to check if conditions for adding tiles Horizontally are true
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @param des variable to store destination coordinate of cell
     * @param sign variable to check direction of movement
     * @return returns true if conditions for adding tiles is true, false otherwise
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to move given cell horizontally
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @param des variable to store destination coordinate of cell
     * @param sign variable to check direction of movement
     */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
            GameScene.this.addToScore(i, des + sign); // Call method to add to score
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    /**
     * Method to check if conditions for adding tiles Vertically are true
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @param des variable to store destination coordinate of cell
     * @param sign variable to check direction of movement
     * @return returns true if conditions for adding tiles is true, false otherwise
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            return cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0;
        return false;
    }

    /**
     * Method to move given cell vertically
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @param des variable to store destination coordinate of cell
     * @param sign variable to check direction of movement
     */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
            GameScene.this.addToScore(des + sign, j); // Call method to add to score
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }

    /**
     * Method to check if adjacent tiles are same
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @return returns true if adjacent tiles are same, false otherwise
     * @author Samien Shaheed-modified
     */
    private boolean haveSameNumber(int i, int j) { // Changed method name from haveSameNumberNearly to haveSameNumber
        // Check if last cell
        if (i == n - 1 && j == n - 1) { 
            if (cells[i - 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            else if (cells[i][j - 1].getNumber() == cells[i][j].getNumber())
                return true;
            else 
                return false;
        }
    
        if(i < n - 1 || j == n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
        }

        if(j < n - 1 || i == n - 1) {
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }

        return false;
    }

    /**
     * Method to check if any valid moves exist
     * @return returns true if no valid moves remaining, false otherwise
     */
    // Check if no more valid moves remaining
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumber(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to keep track of score
     * @param i variable to store i coordinate of cell
     * @param j variable to store j coordinate of cell
     * @author Samien Shaheed-modified
     */
    // Changed method name from sumCellNumbersToScore to addToScore
    private void addToScore(int i, int j) { // Take in the coordinates of a cell as parameter
        score += cells[i][j].getNumber(); // Add the value of the cell at given coordinate to score
    }

    /**
     * Driving method to run the game scene
     * @param gameScene Scene to store elements in the game screen
     * @param primaryStage main stage used for the game
     * @param endGameScene Scene to store elements in the end game screen
     * @param username variable to store username
     * @param darkMode boolean to check if dark mode is enabled
     * @param gridSize variable to check selected grid size
     * @author Samien Shaheed-modified
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot, String username, boolean darkMode, int gridSize) {
        
        setN(gridSize);

        // Generate grid on GUI
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }

        // Store width and height of screen
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Calculate position for score text
        int xPosText = ((screenWidth - gridLength) / 3) + gridLength;
        int xPosScore = ((screenWidth - gridLength) / 2) + gridLength;
        int yPos = screenHeight / 3;

        // Generate Score Text on GUI
        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE");
        text.setFont(Font.font(30));
        text.relocate(xPosText, yPos);

        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(xPosScore, yPos + 50);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        if(darkMode) {
            text.setFill(Color.WHITE);
            scoreText.setFill(Color.WHITE);
        } else {
            text.setFill(Color.rgb(119, 110, 101));
            scoreText.setFill(Color.rgb(119, 110, 101));
        }

        // Add first 2 cells at the start of the game
        randomFillNumber();
        randomFillNumber();

        // Run code when keys pressed
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                                
                int haveEmptyCell;
                
                if (key.getCode() == KeyCode.DOWN) {
                    GameScene.this.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    GameScene.this.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    GameScene.this.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    GameScene.this.moveRight();
                } else {
                    // Throw an exception if any other key is pressed
                    throw new RuntimeException("Invalid Input"); 
                }
                
                scoreText.setText(score + ""); // Update Score on GUI
                
                haveEmptyCell = GameScene.this.haveEmptyCell();
                
                if (haveEmptyCell == -1) { // If no empty cells on grid
                    if (GameScene.this.canNotMove()) {
                        primaryStage.setScene(endGameScene);

                        EndGame.getInstance().endGameShow(endGameRoot, primaryStage, score, username, darkMode);
                        root.getChildren().clear();
                        score = 0;
                    }
                } else if (haveEmptyCell == 1) {
                    randomFillNumber();
                }
            });
        });
    }
}
