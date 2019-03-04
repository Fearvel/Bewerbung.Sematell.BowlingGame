package de.fearvel.bewerbung.sematell.bowlinggame;

import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FallenPinsOverTheLimitException;
import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FrameEndedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Frame class
 * This Frame class contains a round (frame) of a bowling game
 *
 * @author Andreas Schreiner
 * @version 1.0
 */

public class Frame {

    /**
     * Cont of the fallen pins
     */
    private int fallenPins;

    /**
     * count of the throws
     */
    // TODO - Michael Simon: der name throws ist ein ungültiger Name ... daher throwCount
    private int throwCount;

    /**
     * max amount of throws
     */
    private int throwsAvailable;

    /**
     * a boolean which is true if a bonus from prior frames exists
     */
    private boolean bonusFrame;

    /**
     * a list which contains the points of each throw
     */
    private List<Integer> pointsPerThrow;

    /**
     * Constructor
     *
     * @param frame      number of this frame in the game class
     * @param bonusFrame indicates if bonus points will be added
     */
    public Frame(int frame, boolean bonusFrame) {
        pointsPerThrow = new ArrayList<>();
        throwsAvailable = frame == 10 && bonusFrame ? 3 : 2;
        this.bonusFrame = bonusFrame;
    }

    /**
     * Analyses the Throw
     *
     * @param fallenPins count of fallen pins
     * @throws FallenPinsOverTheLimitException throws if more than 10 pins fall
     * @throws FrameEndedException throws if an ended Frame uses throwBall
     */
    public void throwBall(int fallenPins) throws FallenPinsOverTheLimitException, FrameEndedException {
        if (throwCount < throwsAvailable) {
            if (this.fallenPins < 10) {
                if (this.fallenPins + fallenPins > 10) {
                    throw new FallenPinsOverTheLimitException();
                }
                this.fallenPins += fallenPins;
                pointsPerThrow.add(fallenPins);
                throwCount++;
            }
        } else {
            throw new FrameEndedException();
        }
    }

    /**
     * @return The achieved points of this Frame
     */
    public int getPoints() {
        return bonusFrame ? fallenPins * 2 : fallenPins;
    }

    /**
     * @return The count of the fallen pins
     */
    // TODO - Michael Simon: unused ... kann gelöscht werden
    public int getFallenPins() {
        return fallenPins;
    }

    /**
     * @return The count of the trows
     */
    // TODO - Michael Simon: unused ... kann gelöscht werden
    public int getThrows() {
        return throwCount;
    }

    /**
     * @return The type of bonus which got rolled
     */
    public Game.BonusType bonusRolled() {
        if (fallenPins == 10) {
            if (throwCount == 1) {
                return Game.BonusType.STRIKE;
            } else {
                return Game.BonusType.SPARE;
            }
        } else {
            return Game.BonusType.NONE;
        }
    }

    /**
     * @return A boolean which determines if the Frame is ended
     */
    public boolean frameEnded() {
        return fallenPins == 10 || throwCount == throwsAvailable;
    }

    public FrameResult getFrameResult() {
        // TODO - Michael Simon: das List-Interface besitzt keine clone-Methode ... man nutzt dann das "copy-constructor"-Pattern, das Arrayslist anbietet
        return new FrameResult(frameEnded(), getPoints(), fallenPins, (new ArrayList<>(pointsPerThrow)),
                               bonusRolled());
    }

    // TODO - Michael Simon: diese Klasse würde ich komplett rausziehen und den Zugriff ausschließlich über Getter implementieren
    /**
     * Nested class for providing a manipulation proof Result
     */
    public class FrameResult {

        /**
         * boolean value to determine if the frame has ended
         */
        // TODO - Michael Simon: niermals Klassen-Variablen public machen ... der Zugriff sollte immer per "öffentlicher-API" (Getter) durchgeführt werden
        public final boolean frameEnded;

        /**
         * int value for the achieved points
         */
        // TODO - Michael Simon: niermals Klassen-Variablen public machen ... der Zugriff sollte immer per "öffentlicher-API" (Getter) durchgeführt werden
        public final int points;
        /**
         * int value for the count of fallen pins
         */
        // TODO - Michael Simon: niermals Klassen-Variablen public machen ... der Zugriff sollte immer per "öffentlicher-API" (Getter) durchgeführt werden
        public final int pinsFallen;
        /**
         * List which contains every throw of this frame
         */
        // TODO - Michael Simon: niermals Klassen-Variablen public machen ... der Zugriff sollte immer per "öffentlicher-API" (Getter) durchgeführt werden
        public final List<Integer> detailedThrows;
        /**
         * Contains a BonusType different than BonusType.NONE if a bonus is archived
         */
        // TODO - Michael Simon: niermals Klassen-Variablen public machen ... der Zugriff sollte immer per "öffentlicher-API" (Getter) durchgeführt werden
        public final Game.BonusType bonus;


        /**
         * Constructor
         *
         * @param frameEnded     bool value to determine if the frame has ended
         * @param points         int value for the achieved points
         * @param pinsFallen     int value for the count of fallen pins
         * @param detailedThrows ArrayList which contains every throw of this frame
         * @param bonus          Contains a BonusType different than BonusType.NONE if a bonus is archived
         */
        public FrameResult(boolean frameEnded, int points, int pinsFallen, List<Integer> detailedThrows, Game.BonusType bonus) {
            this.frameEnded = frameEnded;
            this.points = points;
            this.pinsFallen = pinsFallen;
            this.detailedThrows = detailedThrows;
            this.bonus = bonus;
        }

        // TODO - Michael Simon: getter
    }
}
