/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Feline object
 */

// Imported Packages
import java.util.Random;

/**
 * This class inherits from Critter and serves as a blueprint for felines
 */
public class Feline extends Critter {
    private int moveCount; //counter for getMove method before random direction
    private int eatCount; //counter for eating
    private Direction currDir; //current direction

    public Feline() {
        super("Fe");
        eatCount = 0;
        moveCount = -1;
    }

    @Override
    public Direction getMove() {
        moveCount++;
        Random random = new Random();
        int randomNumber;
        // Get a random direction using a direction array
        randomNumber = random.nextInt(4);
        Direction[] directionsArray = new Direction[4];
        directionsArray[0] = Direction.NORTH;
        directionsArray[1] = Direction.SOUTH;
        directionsArray[2] = Direction.EAST;
        directionsArray[3] = Direction.WEST;
        // Change direction every 5 moves
        if (moveCount % 5 == 0) {
        currDir = directionsArray[randomNumber];
        }
        return currDir;
    }

    @Override
    public boolean eat() {
        // Eat every 3 times encountering food
        eatCount++;
        if (eatCount % 3 == 0 && eatCount != 0) {
            eatCount = 0;
            return true;
        }
        return false;
    }

    @Override
    public Attack getAttack(String opponent){
       return Attack.POUNCE;
    }
}