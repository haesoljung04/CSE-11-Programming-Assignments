/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Leopard object
 */

// Imported Packages
import java.awt.Color;
import java.util.Random;

/**
 * This class inherits Feline class and serves as a blueprint for leopards
 */
public class Leopard extends Feline {
    protected static int confidence = 0;

    public Leopard() {
        this.displayName = "Lpd";
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override 
    public Direction getMove() {
        // Move in the direction of food and patrick
        Direction[] directionsArray = new Direction[4];
        directionsArray[0] = Direction.NORTH;
        directionsArray[1] = Direction.SOUTH;
        directionsArray[2] = Direction.EAST;
        directionsArray[3] = Direction.WEST;
        for (int i = 0; i < 4; i++) {
            if (this.info.getNeighbor(directionsArray[i]).equals(".") 
            || this.info.getNeighbor(directionsArray[i]).equals("Patrick")) {
                return directionsArray[i];
            }
        }
        // If there is no nearby food or patrick choose a random direction
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt(4);
        return directionsArray[randomNumber];
    }

    @Override
    public boolean eat() {
        // Probability of eating changes on confidence
        Random eatRandom = new Random();
        if (eatRandom.nextInt(1, 101) <= confidence * 10) {
            return true;
        }
        return false;
    }

    @Override
    public void win() {
        if (confidence < 10) {
            confidence++;
        }
    }

    @Override
    public void lose() {
        if (confidence > 0) {
            confidence--;
        }
    }

    @Override
    public Attack getAttack(String opponent) {
        if (opponent.equals("Tu") || confidence > 5) {
            return Attack.POUNCE;
        }
        return generateAttack(opponent);
    }
    // Helper method to chooose random attack
    protected Attack generateAttack(String opponent) {
        if (opponent.equals("Patrick")) {
            return Attack.FORFEIT;
        }
        // Choose a random attack from array using randomly generated number
        Attack[] attacksArray = new Attack[3];
        attacksArray[0] = Attack.POUNCE;
        attacksArray[1] = Attack.SCRATCH;
        attacksArray[2] = Attack.ROAR;
        int randomAttack = random.nextInt(3);
        return attacksArray[randomAttack];
    }

    @Override
    public void reset() {
        confidence = 0;
    }
}
