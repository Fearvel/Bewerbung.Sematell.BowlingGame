package de.Fearvel.Bewerbung.Sematell.BowlingGame;

import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FallenPinsOverTheLimitException;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FrameEndedException;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.GameEndedException;

import java.util.ArrayList;

/**
 * Game class
 * This class contains everything that is necessary for logging a bowling game
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class Game {

    /**
     * an enum which contains the three types of bonuses
     */
    public enum BonusType {
        Strike,
        Spare,
        None
    }

    /**
     * a counter for the remaining bonus frames
     */
    private int _bonusFrames;

    /**
     * a list of frames
     */
    private ArrayList<Frame> _frames;

    /**
     * Constructor
     * initializes the frame list
     * adds a new frame to the frame list
     */
    public Game() {
        _frames = new ArrayList<>();
        _frames.add(new Frame(1, false));
    }

    /**
     * Analyses the passed Throw
     * If the frame has already ended it will create a new frame
     *
     * @param fallenPins the number of fallen pins
     * @throws GameEndedException              throws if the game has already ended and a this method is called
     * @throws FallenPinsOverTheLimitException throws if the combined number of all throws is over 10
     * @throws FrameEndedException             throws if the frame has already ended and the ThrowBall method of the frame is called
     */
    public void ThrowBall(int fallenPins) throws GameEndedException,
            FallenPinsOverTheLimitException, FrameEndedException {
        if (_frames.get(_frames.size() - 1).FrameEnded()) {
            CheckForBonusFrame();
            if (_frames.size() < 10) {
                _frames.add(new Frame(_frames.size() + 1, _bonusFrames > 0));
                if (_bonusFrames > 0) {
                    _bonusFrames--;
                }
            } else {
                throw new GameEndedException();
            }
        }
        _frames.get(_frames.size() - 1).ThrowBall(fallenPins);
    }

    /**
     * Analyzes the latest frame for possible bonuses
     */
    private void CheckForBonusFrame() {
        Frame f = _frames.get(_frames.size() - 1);
        if (f.BonusRolled() != BonusType.None) {
            if (f.BonusRolled() == BonusType.Strike) {
                _bonusFrames = 2;
            } else {
                _bonusFrames = 1;
            }
        }
    }

    /**
     * @return the points of the game
     */
    public int SummarizePoints() {
        int points = 0;
        for (Frame f : _frames) {
            points += f.Points();
        }
        return points;
    }

    /**
     * @return current frame
     */
    public Frame.FrameResult getFrameResultOfTheLatestFrame() {
        return _frames.get(_frames.size() - 1).getFrameResult();
    }

    /**
     * @return a boolean that determines if the game ended
     */
    public boolean GameEnded() {
        return (_frames.size() == 10 &&  _frames.get(_frames.size() - 1).FrameEnded());
    }

    /**
     * This is used as safe way to ensure no Frame gets manipulated
     * @return a FrameResult
     */
    public ArrayList<Frame.FrameResult> getFrameResult() {
        var list = new ArrayList<Frame.FrameResult>();
        for (Frame f : _frames) {
            list.add(f.getFrameResult());
        }
        return list;
    }
}