/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Haesol object
 */

// Imported Packages
import java.awt.Color;
import java.util.Random;

/**
 * This class inherits from Critter and serves as a blueprint for my critter
 */
public class Haesol_haj008 extends Critter{
    // Preotected variables
    protected static int goalX;
    protected static int goalY;

    public Haesol_haj008() {
        super("Jesus");
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }

    @Override
    public boolean eat() {
        // Jesus will only eat with no hostiles around
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
            || this.info.getNeighbor(directionsArray[i]).equals("Jesus")) {
                docileCount++;
            }
        }
        if (docileCount == 4) {
            return true;
        }
        return false;
    }

    @Override
    public Direction getMove() {
        // Determine distance between elephant and goal for x axis
        int goalDistanceX = Math.abs(this.info.getX() - goalX);
        // Determine distance between elephant and goal for y axis
        int goalDistanceY = Math.abs(this.info.getY() - goalY);
        // If an elephant reaches goal change goal to random coordinates
        if (goalDistanceX == 0 && goalDistanceY == 0) {
            Random random = new Random();
            goalX = random.nextInt(this.info.getWidth());
            goalY = random.nextInt(this.info.getHeight());
        }
        // Determine x axis travel direction
        if (goalDistanceX > goalDistanceY) {
            if (goalX > this.info.getX()) {
                return Direction.EAST;
            }
            if (goalX < this.info.getX()) {
                return Direction.WEST;
            }
        }
        // Determine y axis travel direction
        else if (goalDistanceY > goalDistanceX) {
            if (goalY > this.info.getY()) {
                return Direction.SOUTH;
            }
            if (goalY < this.info.getY()) {
                return Direction.NORTH;
            }
        }
        else {
            if (goalX > this.info.getX()) {
                return Direction.EAST;
            }
            if (goalX < this.info.getX()) {
                return Direction.WEST;
            }
        }
        return Direction.CENTER;
    }

    @Override
    public Attack getAttack(String opponent) {
        return generateAttack(opponent);
    }
    protected Attack generateAttack(String opponent) {
        // Choose a random attack
        Attack[] attacksArray = new Attack[3];
        attacksArray[0] = Attack.POUNCE;
        attacksArray[1] = Attack.SCRATCH;
        attacksArray[2] = Attack.ROAR;
        int randomAttack = random.nextInt(3);
        return attacksArray[randomAttack];
    }
}

