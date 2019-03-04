package de.fearvel.bewerbung.sematell.bowlinggame.exceptions;


/**
 * Exception for the case that a throws happen in an Frame, which would result in creating a third or forth throw
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class FrameEndedException extends Exception {

    /**
     * Constructor of this Exception
     *
     * @param message
     */
    // TODO - Michael Simon: ist unused ... kann gelöscht werden
    public FrameEndedException(String message) {
        super(message);
    }

    /**
     * Constructor of this Exception
     */
    public FrameEndedException() {
    }
}
