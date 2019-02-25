package de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions;

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
    public FallenPinsOverTheLimitException(String message) {
        super(message);
    }

    /**
     * Constructor of this Exception
     */
    public FallenPinsOverTheLimitException() {
    }
}
