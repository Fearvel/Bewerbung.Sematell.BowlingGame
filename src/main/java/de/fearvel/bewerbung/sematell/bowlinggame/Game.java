package de.fearvel.bewerbung.sematell.bowlinggame;

import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FallenPinsOverTheLimitException;
import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FrameEndedException;
import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.GameEndedException;

import java.util.ArrayList;
import java.util.List;

/**
 * game class
 * This class contains everything that is necessary for logging a bowling game
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
public class Game {

    private static final int INIT_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_COUNT = 10;

    /**
     * an enum which contains the three types of bonuses
     */
    // TODO - Michael Simon: Enum-Ausprägungen werden auch immer Uppercase geschrieben
    public enum BonusType {
        STRIKE,
        SPARE,
        NONE
    }

    /**
     * a counter for the remaining bonus frames
     */
    private int bonusFrames;

    /**
     * a list of frames
     */
    private List<Frame> frames;

    /**
     * Constructor
     * initializes the frame list
     * adds a new frame to the frame list
     */
    public Game() {
        frames = new ArrayList<>();
        // TODO - Michael Simon: die 1 ist eine "magic number" ... oftmals ist nicht sofort klar, was die Zahl gedeutet, daher lagert man sie in einem Variable aus
        frames.add(new Frame(INIT_FRAME_NUMBER, false));
    }

    /**
     * Analyses the passed Throw
     * If the frame has already ended it will create a new frame
     *
     * @param fallenPins the number of fallen pins
     * @throws GameEndedException              throws if the game has already ended and a this method is called
     * @throws FallenPinsOverTheLimitException throws if the combined number of all throws is over 10
     * @throws FrameEndedException             throws if the frame has already ended and the throwBall method of the frame is called
     */
    // TODO - Michael Simon: dein Exception-Handling hat mir gut gefallen
    public void throwBall(int fallenPins) throws GameEndedException, FallenPinsOverTheLimitException, FrameEndedException {
        if (frames.get(frames.size() - 1).frameEnded()) {
            checkForBonusFrame();
            if (frames.size() < MAX_FRAME_COUNT) {
                frames.add(new Frame(frames.size() + 1, bonusFrames > 0));
                if (bonusFrames > 0) {
                    bonusFrames--;
                }
            } else {
                throw new GameEndedException();
            }
        }
        frames.get(frames.size() - 1).throwBall(fallenPins);
    }

    /**
     * @return the points of the game
     */
    public int summarizePoints() {
        int points = 0;
        for (Frame f : frames) {
            points += f.getPoints();
        }
        return points;
    }

    /**
     * @return current frame
     */
    public Frame.FrameResult getFrameResultOfTheLatestFrame() {
        return frames.get(frames.size() - 1).getFrameResult();
    }

    /**
     * @return a boolean that determines if the game ended
     */
    public boolean gameEnded() {
        return (frames.size() == MAX_FRAME_COUNT && frames.get(frames.size() - 1).frameEnded());
    }

    /**
     * This is used as safe way to ensure no Frame gets manipulated
     * @return a FrameResult
     */
    // TODO - Michael Simon: verwende niemal konkrete Implementierungen wie ArrayList ... nutze immer List ... List ist nur ein Interface und die eigentliche Implementierung kann man dann nach belieben austauschen. Es gibt ja unzälige Implementierungen mit gewissen vor und Nachteilen
    public List<Frame.FrameResult> getFrameResult() {
        var frameResults = new ArrayList<Frame.FrameResult>();
        for (Frame frame : frames) {
            frameResults.add(frame.getFrameResult());
        }
        return frameResults;
    }

    // TODO - Michael Simon: ich persönlich sortiere die Methoden in einer Klasse immer nach ihrer Sichtbarkeit public > protected > package-private> private
    /**
     * Analyzes the latest frame for possible bonuses
     */
    private void checkForBonusFrame() {
        Frame frame = frames.get(frames.size() - 1);
        if (frame.bonusRolled() != BonusType.NONE) {
            if (frame.bonusRolled() == BonusType.STRIKE) {
                // TODO - Michael Simon: hier könntest du die 2 und 1 auch in eine benamte Varaible auslagern ... damit klar ist, was sie aussagen
                bonusFrames = 2;
            } else {
                bonusFrames = 1;
            }
        }
    }
}
