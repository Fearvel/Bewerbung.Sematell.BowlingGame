package de.fearvel.bewerbung.sematell.bowlinggame.exceptions;

/**
 * Exception for the case that a throws happen in an game, which would result in creating a 11 frame
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
    // TODO - Michael Simon: ist unused ... kann gelöscht werden
    public GameEndedException(String message) {
        super(message);
    }

    /**
     * Constructor of this Exception
     */
    public GameEndedException() {
    }
}
