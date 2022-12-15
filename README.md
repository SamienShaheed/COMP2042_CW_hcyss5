# Software-Maintenance-CW-2022
 
Name: Samien Shaheed<br/>
ID: 20291244<br/>
<br/>
<br/>

## How to compile the code <br/>
Open Software Maintanence folder as a project on IntelliJ. Add JavaFX libray and run Main.Java. <br/>
<br/>
<br/>

## Javadoc Documentation <br/>
javadoc file in repository
<br/>
<br/>

## List of implemented features working properly <br/>
* Light Mode and Dark Mode themes
* Classic(4x4), Large(5x5) and Wide(6x6) Game Modes
* Main Menu Screen with Quit Button and Text Field for username
* Improved Game Over Screen with Quit and Play Again Button

## List of implemented features not working properly <br/>
* Leaderboard System - not implemented with player names, only with scores

## List of Java classes modified
1. Main.java
  *  Added a getter method for Width and Height variables
  *  Broke down start method into various smaller methods
  *  Added various methods to display fxml files
  *  Handles dark mode and light mode themes  
2. GameScene.java
  * Improved passDestination function from if else to switch case
  * Renamed methods to better describe functions
  * Improved functionality of haveSameNumber method for general use
  * Renamed sumCellToNumbersToScore method to addToScore method
  * addToScore method rewritten to properly count score after cells have been added
  * Text.relocate done by calculating screen width and height
  * Fixed bug where new tiles appear even if input wasn't valid
  * Fixed bug where score increases with any input  
3. EndGame.java
  * Improved GUI of EndGame Screen
  * Implemented Play Again Button
  * Fixed bug where Quit Button would not exit window
  * Implemented a display of highest scores for leaderboard  
4. Controller.java
  * Added fxml variables and methods to handle fxml files
  * is the controller class for all fxml files  
5. Cell.java
  * Improved Cell Colors to match original game
  * Added method to set Text properties of Cells depending on it's value
