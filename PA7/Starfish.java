/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Starfish object
 */

// Imported Packages
import java.awt.Color;

/**
 * This class inherits the critter class and serves as a blueprint for starfish
 */
public class Starfish extends Critter {
    private static final String SPECIES_NAME = "Patrick";

    /**
     * Default constructor - creates critter with name Patrick
     */
    public Starfish() {
        super(SPECIES_NAME);
    }

    @Override
    public Direction getMove() {
        return Direction.CENTER;
    }

    /**
     * Returns the color of the Starfish
     * 
     * @return Color pink
     */
    @Override 
    public Color getColor() {
        return Color.PINK;
    }
}