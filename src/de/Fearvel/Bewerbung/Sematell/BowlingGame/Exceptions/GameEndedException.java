package de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions;

/**
 * Exception for the case that a throws happen in an Game, which would result in creating a 11 frame
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class GameEndedException extends Exception {

    /**
     * Constructor of this Exception
     *
     * @param message
     */
    public GameEndedException(String message) {
        super(message);
    }

    /**
     * Constructor of this Exception
     */
    public GameEndedException() {
    }
}
