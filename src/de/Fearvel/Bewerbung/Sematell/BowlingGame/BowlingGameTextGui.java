package  de.Fearvel.Bewerbung.Sematell.BowlingGame;

import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FallenPinsOverTheLimitException;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.FrameEndedException;
import de.Fearvel.Bewerbung.Sematell.BowlingGame.Exceptions.GameEndedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BowlingGameTextGui class
 * This class will display a simple text-gui
 *
 * @author Andreas Schreiner
 * @version 1.0
 */

public class BowlingGameTextGui {

    /**
     * Long separator line
     */
    private final String _line = "--------------------------------------------------------------------\n";

    /**
     * shorter separator line
     */
    private final String _subLine = "--------------------------------------------------------";

    /**
     * instance of a Game
     */
    private Game _game;


    /**
     * Runs the TextGui
     */
    public void Run() {
        _game = new Game();
        ShowDialog();
    }

    /**
     * Entry point of the Dialog
     */
    private void ShowDialog() {
        while (true) {
            ShowPoints();
            if (!_game.GameEnded()) {
                ShowRollDialog();
            } else {
                ShowAfterGameDialog();
            }
        }
    }

    /**
     * Displays the Points of the Game
     */
    private void ShowPoints() {
        System.out.println("Punkteübersicht\n" + _line);
        var list = _game.getFrameResult();
        for (int i = 0; i < list.size(); i++) {
            int framePoints = 0;
            ArrayList<Integer> subList = list.get(i).DetailedThrows;
            String formattedOutput;

            formattedOutput = "Frame " + (i + 1) + ((i + 1) < 10 ? "   " : "  ") + "|";
            for (int j = 0; j < subList.size(); j++) {
                formattedOutput += " Roll " + (j + 1) + ": " + subList.get(j);
                framePoints += subList.get(j);
            }
            for (int j = formattedOutput.length(); j < 45; j++) {
                formattedOutput += " ";
            }
            if (framePoints == 10 && subList.size() == 1) {
                formattedOutput += "| STRIKE";
            }
            if (framePoints == 10 && subList.size() > 1) {
                formattedOutput += "| SPARE";
            }
            for (int j = formattedOutput.length(); j < 55; j++) {
                formattedOutput += " ";
            }
            formattedOutput += "| Points: " + list.get(i).Points;
            System.out.println(formattedOutput);
        }
        System.out.println(_subLine + " Points: " + _game.SummarizePoints());

    }

    /**
     * Displays the dialog options for an active game
     */
    private void ShowRollDialog() {
        System.out.print(_line + "(1) Roll Random\n(2) Roll value\n(3) Exit\n->: ");
        try {
            switch (ReadInt()) {
                case 1:
                    if (!_game.getFrameResultOfTheLatestFrame().FrameEnded) {
                        _game.ThrowBall(ThreadLocalRandom.current().
                                nextInt(0, 11 - _game.getFrameResultOfTheLatestFrame().PinsFallen));
                    } else {
                        _game.ThrowBall(ThreadLocalRandom.current().nextInt(0, 11));
                    }
                    break;
                case 2:
                    System.out.print("Please enter a number: \n");
                    _game.ThrowBall(ReadInt());
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.print("Please enter a number between 1-3 inclusive\n");
                    break;
            }
        } catch (NumberFormatException | IOException | GameEndedException e) {
            System.out.print("Please enter a valid Number");
        } catch (FallenPinsOverTheLimitException e) {
            System.out.print("Please enter a number between 0-" +
                    (10 - _game.getFrameResultOfTheLatestFrame().PinsFallen) + " inclusive\n");
        } catch (FrameEndedException e) {
            System.out.print("Something went terrible wrong");
        }
    }

    /**
     * Parses an int from the Console
     * @return an int value
     * @throws IOException
     * @throws NumberFormatException if entered String is not a string that contains only integer values
     */
    private int ReadInt() throws IOException, NumberFormatException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return Integer.parseInt(br.readLine());
    }

    /**
     * Displays dialog options for a completed game
     */
    private void ShowAfterGameDialog() {
        System.out.print(_line + "(1) New Game\n(2) Exit\n->: ");
        try {
            switch (ReadInt()) {
                case 1:
                    _game = new Game();
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.print("Please enter a number between 1-2 inclusive\n");
                    break;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.print("Please enter a valid Number");
        }
    }
}