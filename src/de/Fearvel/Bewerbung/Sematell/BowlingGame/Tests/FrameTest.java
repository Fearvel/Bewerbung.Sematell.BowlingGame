package  de.Fearvel.Bewerbung.Sematell.BowlingGame.Tests;

import de.Fearvel.Bewerbung.Sematell.BowlingGame.Frame;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test-class for the Frame Class
 *
 * @author Andreas Schreiner
 * @version 1.0
 */
class FrameTest {

    /**
     * Fills a frame.
     * this test will fail if an error is thrown
     */
    @Test
    void throwBall() {
        CreateNormalFrame(); //assertDoesNotThrow via function below
        CreateExtendedFrame(); //assertDoesNotThrow via function below
    }

    /**
     * Checks if the points are calculated correctly
     */
    @Test
    void points() {
        var normalFrame = CreateNormalFrame();
        assertTrue(normalFrame.Points() ==normalFrame.getFallenPins());
        var extendedFrame = CreateExtendedFrame();
        assertTrue(extendedFrame.Points() == extendedFrame.getFallenPins()*2);
    }

    /**
     * Checks if the fallen pin count is equal to the points of a non bonus game, which it should be
     */
    @Test
    void getFallenPins() {
        var normalFrame = CreateNormalFrame();
        assertTrue(normalFrame.getFallenPins() == normalFrame.Points());
    }

    /**
     * Compares the size of the Arraylist of integer with the max throws
     */
    @Test
    void getThrows() {
        assertTrue(CreateNormalFrame().getThrows() ==2);
        assertTrue(CreateExtendedFrame().getThrows() ==3);
    }

    /**
     * Checks if the Frames change correctly to the value True on frame ended if the ending criteria is met
     */
    @Test
    void frameEnded() {
        assertTrue(CreateNormalFrame().FrameEnded());
        assertTrue(CreateExtendedFrame().FrameEnded());
        assertTrue(CreateStrikeFrame().FrameEnded());
    }

    /**
     * Checks if a FrameResult can be created
     */
    @Test
    void getFrameResult() {
        assertTrue(CreateNormalFrame().getFrameResult().FrameEnded);
    }
    /**
     * @return creates a 2 throw Frame
     */
    private Frame CreateNormalFrame() {
        Frame normalFrame = new Frame(1, false);
        assertDoesNotThrow(() -> {
            normalFrame.ThrowBall(1);
            normalFrame.ThrowBall(ThreadLocalRandom.current().nextInt(0, 10));
        });
        return normalFrame;
    }

    /**
     * @return creates a 3 throw Frame
     */
    private Frame CreateExtendedFrame() {
        Frame extendedFrame = new Frame(10, true);
        assertDoesNotThrow(() -> {
            extendedFrame.ThrowBall(1);
            extendedFrame.ThrowBall(1);
            extendedFrame.ThrowBall(ThreadLocalRandom.current().nextInt(0, 9));
        });
        return extendedFrame;
    }

    /**
     * @return creates a Frame in which the throw is a strike
     */
    private Frame CreateStrikeFrame() {
        Frame extendedFrame = new Frame(10, true);
        assertDoesNotThrow(() -> {
            extendedFrame.ThrowBall(10);
        });
        return extendedFrame;
    }
}