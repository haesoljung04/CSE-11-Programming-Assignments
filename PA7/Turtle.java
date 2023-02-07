/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Turtle object
 */

// Imported Packages
import java.awt.Color;
import java.util.Random;

/**
 * This class inherits the critter class and serves as a blueprint for turtles
 */
public class Turtle extends Critter {
    
    public Turtle() {
        super("Tu");
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Direction getMove() {
        return Direction.WEST;
    }

    @Override
    public boolean eat() {
        // Turtles will only eat if there are no hostile people around
        Direction[] directionsArray = new Direction[4];
        directionsArray[0] = Direction.NORTH;
        directionsArray[1] = Direction.SOUTH;
        directionsArray[2] = Direction.EAST;
        directionsArray[3] = Direction.WEST;
        int docileCount = 0;
        for (int i = 0; i < 4; i++) {
            // Check for hostiles
            if (this.info.getNeighbor(directionsArray[i]).equals(" ") 
            || this.info.getNeighbor(directionsArray[i]).equals(".")
            || this.info.getNeighbor(directionsArray[i]).equals("Tu")) {
                docileCount++;
            }
        }
        if (docileCount == 4) {
            return true;
        }
        return false;
    }
    
    @Override
    public Attack getAttack(String opponent) {
        // Attack with Roar or forfeit 50% chance
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            return Attack.ROAR;
        }
        else {
            return Attack.FORFEIT;
        }
    }
}
