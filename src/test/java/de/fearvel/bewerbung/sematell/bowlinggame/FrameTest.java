package de.fearvel.bewerbung.sematell.BowlingGame;

import de.fearvel.bewerbung.sematell.bowlinggame.Frame;
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
    // TODO - Michael Simon: Kommentare sind immer ein Blick in der Vergangenheit ... in einem Jahr weiß ich nicht mehr, ob sie stimmen ... daher löschen
    createNormalFrame(); //assertDoesNotThrow via function below
    createExtendedFrame(); //assertDoesNotThrow via function below
  }

  /**
   * Checks if the points are calculated correctly
   */
  @Test
  void points() {
    var normalFrame = createNormalFrame();
    // TODO - Michael Simon: hier solltest du assertEquals verwenden ... assertEquals(expected, actual)
    assertTrue(normalFrame.getPoints() ==normalFrame.getFallenPins());

    // TODO - Michael Simon: dies ist ein anderer Testcase, der in eine separate TestMethode gehört
    var extendedFrame = createExtendedFrame();
    // TODO - Michael Simon: hier solltest du assertEquals verwenden ... assertEquals(expected, actual)
    assertTrue(extendedFrame.getPoints() == extendedFrame.getFallenPins()*2);
  }

  /**
   * Checks if the fallen pin count is equal to the points of a non bonus game, which it should be
   */
  @Test
  void getFallenPins() {
    var normalFrame = createNormalFrame();
    // TODO - Michael Simon: assertEquals
    assertTrue(normalFrame.getFallenPins() == normalFrame.getPoints());
  }

  /**
   * Compares the size of the Arraylist of integer with the max throws
   */
  @Test
  void getThrows() {
    // TODO - Michael Simon: assertEquals und "magic numbers"
    assertTrue(createNormalFrame().getThrows() ==2);
    assertTrue(createExtendedFrame().getThrows() ==3);
  }

  /**
   * Checks if the Frames change correctly to the value True on frame ended if the ending criteria is met
   */
  @Test
  void frameEnded() {
    // TODO - Michael Simon: assertEquals
    assertTrue(createNormalFrame().frameEnded());
    assertTrue(createExtendedFrame().frameEnded());
    assertTrue(createStrikeFrame().frameEnded());
  }

  /**
   * Checks if a FrameResult can be created
   */
  @Test
  void getFrameResult() {
    assertTrue(createNormalFrame().getFrameResult().frameEnded);
  }
  /**
   * @return creates a 2 throw Frame
   */
  private Frame createNormalFrame() {
    // TODO - Michael Simon:  magic numbers
    Frame normalFrame = new Frame(1, false);
    assertDoesNotThrow(() -> {
      normalFrame.throwBall(1);
      normalFrame.throwBall(ThreadLocalRandom.current().nextInt(0, 10));
    });
    return normalFrame;
  }

  /**
   * @return creates a 3 throw Frame
   */
  private Frame createExtendedFrame() {
    // TODO - Michael Simon:  magic numbers
    Frame extendedFrame = new Frame(10, true);
    assertDoesNotThrow(() -> {
      extendedFrame.throwBall(1);
      extendedFrame.throwBall(1);
      extendedFrame.throwBall(ThreadLocalRandom.current().nextInt(0, 9));
    });
    return extendedFrame;
  }

  /**
   * @return creates a Frame in which the throw is a strike
   */
  private Frame createStrikeFrame() {
    // TODO - Michael Simon:  magic numbers
    Frame extendedFrame = new Frame(10, true);
    assertDoesNotThrow(() -> extendedFrame.throwBall(10));
    return extendedFrame;
  }
}
