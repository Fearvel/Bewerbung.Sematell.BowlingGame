package de.fearvel.bewerbung.sematell.bowlinggame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit Test-class for the game Class
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

    createCompletedGame(); // assertion via function below
  }

  /**
   * Tests if the returned points are in a valid state
   */
  @Test
  void summarizePoints() {
    var game = createCompletedGame();
    final int pointsSummarized = game.summarizePoints();
    int points = 0;

    for (Frame.FrameResult res : game.getFrameResult()) {
      points += res.points;
    }
    assertTrue(pointsSummarized == points);
  }

  /**
   * Tests if a FrameResult of the latest frame can be Created
   */
  @Test
  void getFrameResultOfTheLatestFrame() {
    var game = createCompletedGame();
    assertDoesNotThrow(() -> {
      game.getFrameResultOfTheLatestFrame();
    });
  }

  /**
   * Tests if the generated test game has ended
   */
  @Test
  void gameEnded() {
    var game = createCompletedGame();
    Assertions.assertTrue(game.gameEnded());

  }

  /**
   * Tests if a ArrayList of FrameResults can be Created
   */
  @Test
  void getFrameResult() {
    var game = createCompletedGame();
    assertDoesNotThrow(() -> {
      game.getFrameResult();
    });
  }

  /**
   * @return a ended complete game
   */
  private Game createCompletedGame() {
    Game g = new Game();
    assertDoesNotThrow(() -> {
      while (!g.gameEnded()) {
        g.throwBall(ThreadLocalRandom.current().
            nextInt(0, 11 - g.getFrameResultOfTheLatestFrame().pinsFallen));
      }
    });
    return g;
  }

  // TODO - Michael Simon: da dein Programm nicht auf den maximalen Score von 300 kommt, erstelle am besten einen Unittest für ein Max-Game und zähle die Punkte
}
