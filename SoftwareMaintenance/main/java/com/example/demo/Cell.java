package com.example.demo;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Handles the generation, formatting, logic of individual tiles
 * @author Samien Shaheed-modified
 */
public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    void setModify(boolean modify) {
        this.modify = modify;
    }

    boolean getModify() {
        return modify;
    }

    /**
     * Constructor method for tiles
     * @param x X coordinate of the cell
     * @param y Y coordinate of the cell
     * @param scale Size of the cell
     * @author Samien Shaheed-modified
     */
    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(204, 192, 179, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Method to swap cells
     * @param cell cell to be swapped with
     */
    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);
        setTextByNumber(cell);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Adds a cell to it's adjacent cell
     * @param cell stores the cell that needs to be added
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        setTextByNumber(cell);
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Method to assign cell color depending on value
     * @param number stores the value of a cell
     * @author Samien Shaheed-modified
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0 -> rectangle.setFill(Color.rgb(204, 192, 179, 0.5));
            case 2 -> rectangle.setFill(Color.rgb(238, 228, 218));
            case 4 -> rectangle.setFill(Color.rgb(237, 224, 200));
            case 8 -> rectangle.setFill(Color.rgb(242, 177, 121));
            case 16 -> rectangle.setFill(Color.rgb(245, 149, 99));
            case 32 -> rectangle.setFill(Color.rgb(246, 124, 95));
            case 64 -> rectangle.setFill(Color.rgb(246, 94, 59));
            case 128 -> rectangle.setFill(Color.rgb(237, 207, 114));
            case 256 -> rectangle.setFill(Color.rgb(237, 204, 97));
            case 512 -> rectangle.setFill(Color.rgb(237, 200, 80));
            case 1024 -> rectangle.setFill(Color.rgb(237, 197, 63));
            case 2048 -> rectangle.setFill(Color.rgb(237, 194, 46));
        }
    }

    /**
     * Method to change text size of a cell depending on it's value
     * @author Samien Shaheed-modified
     */
    void setTextByNumber(Cell cell) {
        if(numberOfDigits(cell.getNumber()) > 2) {
            cell.getTextClass().setFont(Font.font((3 * GameScene.getLENGTH()) / 9.0));
        }

        if(cell.getNumber() > 4) {
            cell.getTextClass().setFill(Color.WHITE);
        }
    }

    /**
     * Method that returns the number of digits of the cell's value
     * @param number stores the value of a cell
     * @return number of digits in the number
     * @author Samien Shaheed
     */
    int numberOfDigits(int number) {
        int count = 0;
        while(number != 0) {
            number /= 10;
            ++count;
        }
        return count;
    }

    double getX() {
        return rectangle.getX();
    }

    double getY() {
        return rectangle.getY();
    }

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    private Text getTextClass() {
        return textClass;
    }

}
