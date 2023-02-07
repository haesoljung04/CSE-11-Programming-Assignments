/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for an elephant object
 */

// Imported Packages
import java.awt.Color;
import java.util.Random;
 
/**
 * This class serves as a blueprint for elephant objects
 */
public class Elephant extends Critter{
    // Protected Variables
    protected static int goalX;
    protected static int goalY;

    public Elephant() {
        super("El");
        goalX = 0;
        goalY = 0;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
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
    public boolean eat() {
        return true;
    }

    @Override
    public void mate() {
        this.incrementLevel(2);
    }

    @Override
    public void reset() {
        goalX = 0;
        goalY = 0;
    }
}
