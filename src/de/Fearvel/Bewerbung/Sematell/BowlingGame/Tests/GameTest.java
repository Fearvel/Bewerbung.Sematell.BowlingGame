package  de.Fearvel.Bewerbung.Sematell.BowlingGame.Tests;

import de.Fearvel.Bewerbung.Sematell.BowlingGame.Frame;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit Test-class for the Game Class
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
class GameTest {

    /**
     * Tests if Balls can be Thrown
     */
    @Test
    void throwBall() {

        CreateCompletedGame(); // assertion via function below
    }

    /**
     * Tests if the returned points are in a valid state
     */
    @Test
    void summarizePoints() {
        var game = CreateCompletedGame();
        final int pointsSummarized = game.SummarizePoints();
        int points = 0;

        for (Frame.FrameResult res : game.getFrameResult()) {
            points += res.Points;
        }
        assertTrue(pointsSummarized == points);
    }

    /**
     * Tests if a FrameResult of the latest frame can be Created
     */
    @Test
    void getFrameResultOfTheLatestFrame() {
        var game = CreateCompletedGame();
        assertDoesNotThrow(() -> {
            game.getFrameResultOfTheLatestFrame();
        });
    }

    /**
     * Tests if the generated test game has ended
     */
    @Test
    void gameEnded() {
        var game = CreateCompletedGame();
        Assertions.assertTrue(game.GameEnded());

    }

    /**
     * Tests if a ArrayList of FrameResults can be Created
     */
    @Test
    void getFrameResult() {
        var game = CreateCompletedGame();
        assertDoesNotThrow(() -> {
            game.getFrameResult();
        });
    }

    /**
     * @return a ended complete game
     */
    private Game CreateCompletedGame() {
        Game g = new Game();
        assertDoesNotThrow(() -> {
            while (!g.GameEnded()) {
                g.ThrowBall(ThreadLocalRandom.current().
                        nextInt(0, 11 - g.getFrameResultOfTheLatestFrame().PinsFallen));
            }
        });
        return g;
    }
}