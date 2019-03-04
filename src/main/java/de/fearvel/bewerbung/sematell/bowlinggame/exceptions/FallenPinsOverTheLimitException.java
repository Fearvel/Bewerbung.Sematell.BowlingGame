package de.fearvel.bewerbung.sematell.bowlinggame.exceptions;

/**
 * Exception for the case that more than 11 pins should fall
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class FallenPinsOverTheLimitException extends Exception {

    /**
     * Constructor of this Exception
     *
     * @param message
     */
    // TODO - Michael Simon: ist unused ... kann gelöscht werden
    public FallenPinsOverTheLimitException(String message) {
        super(message);
    }

    /**
     * Constructor of this Exception
     */
    public FallenPinsOverTheLimitException() {
    }
}
