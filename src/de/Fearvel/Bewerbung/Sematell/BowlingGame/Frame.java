package de.Fearvel.Bewerbung.Sematell.BowlingGame;

import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FallenPinsOverTheLimitException;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FrameEndedException;
import java.util.ArrayList;

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
    private int _fallenPins;

    /**
     * count of the throws
     */
    private int _throws;

    /**
     * max amount of throws
     */
    private int _throwsAvailable;

    /**
     * a boolean which is true if a bonus from prior frames exists
     */
    private boolean _bonusFrame;

    /**
     * a list which contains the points of each throw
     */
    private ArrayList<Integer> _pointsPerThrow;

    /**
     * Constructor
     *
     * @param frame      number of this frame in the Game class
     * @param bonusFrame indicates if bonus points will be added
     */
    public Frame(int frame, boolean bonusFrame) {
        _pointsPerThrow = new ArrayList<>();
        _throwsAvailable = frame == 10 && bonusFrame ? 3 : 2;
        _bonusFrame = bonusFrame;
    }

    /**
     * Analyses the Throw
     *
     * @param fallenPins count of fallen pins
     * @throws FallenPinsOverTheLimitException throws if more than 10 pins fall
     * @throws FrameEndedException throws if an ended Frame uses ThrowBall
     */
    public void ThrowBall(int fallenPins) throws FallenPinsOverTheLimitException, FrameEndedException {
        if (_throws < _throwsAvailable) {
            if (_fallenPins < 10) {
                if (_fallenPins + fallenPins > 10) {
                    throw new FallenPinsOverTheLimitException();
                }
                _fallenPins += fallenPins;
                _pointsPerThrow.add(fallenPins);
                _throws++;
            }
        } else {
            throw new FrameEndedException();
        }
    }

    /**
     * @return The achieved points of this Frame
     */
    public int Points() {
        return _bonusFrame ? _fallenPins * 2 : _fallenPins;
    }

    /**
     * @return The count of the fallen pins
     */
    public int getFallenPins() {
        return _fallenPins;
    }

    /**
     * @return The count of the trows
     */
    public int getThrows() {
        return _throws;
    }

    /**
     * @return The type of bonus which got rolled
     */
    public Game.BonusType BonusRolled() {
        if (_fallenPins == 10) {
            if (_throws == 1) {
                return Game.BonusType.Strike;
            } else {
                return Game.BonusType.Spare;
            }
        } else {
            return Game.BonusType.None;
        }
    }

    /**
     * @return A boolean which determines if the Frame is ended
     */
    public boolean FrameEnded() {
        return _fallenPins == 10 || _throws == _throwsAvailable;
    }

    public FrameResult getFrameResult() {
        return new FrameResult(FrameEnded(), Points(), _fallenPins, ((ArrayList<Integer>) _pointsPerThrow.clone()),
                BonusRolled());
    }

    /**
     * Nested class for providing a manipulation proof Result
     */
    public class FrameResult {

        /**
         * boolean value to determine if the frame has ended
         */
        public final boolean FrameEnded;

        /**
         * int value for the achieved points
         */
        public final int Points;
        /**
         * int value for the count of fallen pins
         */
        public final int PinsFallen;
        /**
         * ArrayList which contains every throw of this frame
         */
        public final ArrayList<Integer> DetailedThrows;
        /**
         * Contains a BonusType different than BonusType.None if a bonus is archived
         */
        public final Game.BonusType Bonus;


        /**
         * Constructor
         *
         * @param frameEnded     bool value to determine if the frame has ended
         * @param points         int value for the achieved points
         * @param pinsFallen     int value for the count of fallen pins
         * @param detailedThrows ArrayList which contains every throw of this frame
         * @param bonus          Contains a BonusType different than BonusType.None if a bonus is archived
         */
        public FrameResult(boolean frameEnded, int points, int pinsFallen, ArrayList<Integer> detailedThrows, Game.BonusType bonus) {

            FrameEnded = frameEnded;
            Points = points;
            PinsFallen = pinsFallen;
            DetailedThrows = detailedThrows;
            Bonus = bonus;
        }
    }
}