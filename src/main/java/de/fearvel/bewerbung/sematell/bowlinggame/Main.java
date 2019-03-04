package de.fearvel.bewerbung.sematell.bowlinggame;

/**
 * Main class
 * This Frame class runs an instance of BowlingGameTextGui
 * Its just the entry point.
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class Main {

    /**
     * Main Class
     * @param args unused parameters
     */
    public static void main(String [] args) {
        var game = new BowlingGameTextGui();
        game.Run();
    }
}
