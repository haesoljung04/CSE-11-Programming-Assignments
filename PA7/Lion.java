/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Lion object
 */

// Imported Packages
import java.awt.Color;

/**
 * This class inherits the Feline class and serves as a blueprint for lions
 */
public class Lion extends Feline {
    /** Instance variables */
    private int fightCount = 0; // Counter for fights won
    private int moveCount = 0; // Counter for number of times moved
    private String lionName = "Lion"; // String representation of lion

    public Lion() {
        this.displayName = lionName;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
    @Override
    public Direction getMove() {
        // Series of cases that make lion move in square shape
        if (moveCount < 5) {
            moveCount++;
            return Direction.EAST;
        }
        else if (moveCount < 10) {
            moveCount++;
            return Direction.SOUTH;
        }
        else if (moveCount < 15) {
            moveCount++;
            return Direction.WEST;
        }
        else if (moveCount < 20) {
            moveCount++;
            return Direction.NORTH;
        }
        // If the lion completed the full clockwise rotation
        else {
            moveCount = 1;
            return Direction.EAST;
        }
    }

    @Override
    public void win() {
        fightCount++;
    }

    @Override
    public void sleep() {
        fightCount = 0;
        this.displayName = "noiL";
    }
    
    @Override
    public void wakeup() {
        this.displayName = lionName;
    }

    @Override
    public boolean eat() {
        if(fightCount == 0) {
            return false;
        }
        else {
            fightCount = 0;
            return true;
        }
    }
}
