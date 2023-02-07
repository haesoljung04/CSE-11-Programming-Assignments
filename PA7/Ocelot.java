/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * Sources used: Textbook, Lecture Notes, Prior Knowledge, Oracle Doc
 * 
 * This file implements the behavior for a Ocelot object
 */

// Imported Packages
import java.awt.Color;

/**
 * This class inherits the Leopard class and serves as a blueprint for ocelots
 */
public class Ocelot extends Leopard {
    public Ocelot() {
        this.displayName = "Oce";
    }

    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    protected Attack generateAttack(String opponent) {
        // If they are a feline then they will be scratched
        if (opponent.equals("Lion") 
        || opponent.equals("Fe") 
        || opponent.equals("Lpd")) {
            return Attack.SCRATCH;
        }
        // Not a feline then pounce
        return Attack.POUNCE;
    }
}
