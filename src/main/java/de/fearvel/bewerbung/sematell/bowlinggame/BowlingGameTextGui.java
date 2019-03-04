package de.fearvel.bewerbung.sematell.bowlinggame;

import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FallenPinsOverTheLimitException;
import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.FrameEndedException;
import de.fearvel.bewerbung.sematell.bowlinggame.exceptions.GameEndedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
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
    // TODO - Michael Simon: hierbei handelt es sich um eine Konstante ... diese sollte immer mit static deklariert werden, als uppercase geschrieben werden und Zeichen wie "_"
    private static final String LINE = "--------------------------------------------------------------------\n";

    /**
     * shorter separator line
     */
    // TODO - Michael Simon: same here
    private static final String SUB_LINE = "--------------------------------------------------------";

    /**
     * instance of a game
     */
    // TODO - Michael Simon: ich kenne diese Notation mit "_" aus JS ... Thema async this vs that ... in Java sind die nicht notwendig
    private Game game;


    /**
     * Runs the TextGui
     */
    public void Run() {
        game = new Game();
        showDialog();
    }

    /**
     * Entry point of the Dialog
     */
    // TODO - Michael Simon: Funktionsnamen werden immer in camelCase geschrieben
    private void showDialog() {
        while (true) {
            showPoints();
            if (!game.gameEnded()) {
                showRollDialog();
            } else {
                showAfterGameDialog();
            }
        }
    }

    /**
     * Displays the getPoints of the game
     */
    private void showPoints() {
        // TODO - Michael Simon: verwendete am besten immer System.lineSeparator(), da du niemals weißt, auf was für einen System dein Programm ausgeführt wird
        System.out.println("Punkteübersicht" + System.lineSeparator() + LINE);
        // TODO - Michael Simon: verwende immer assagekräftige Namen
        var frameResult = game.getFrameResult();
        for (int i = 0; i < frameResult.size(); i++) {
            int framePoints = 0;
            List<Integer> subList = frameResult.get(i).detailedThrows;
            String formattedOutput;

            
            // TODO - Michael Simon: ich würde hier den StringBuilder verwenden
            // TODO - Michael Simon: ich kann innerhalb der ersten 5 Sekunden nicht zu 100% erkennne, wie die nächste Zeile funktioniert. Daher würde ich die String-Konkatenation in eine Funktion auslagern und einen passenden Namen vergeben
            formattedOutput = "Frame " + (i + 1) + ((i + 1) < 10 ? "   " : "  ") + "|";
            for (int j = 0; j < subList.size(); j++) {
                formattedOutput += " Roll " + (j + 1) + ": " + subList.get(j);
                framePoints += subList.get(j);
            }
            for (int j = formattedOutput.length(); j < 45; j++) {
                formattedOutput += " ";
            }
            // TODO - Michael Simon: 10 ist eine magic number ... daher auslagern
            if (framePoints == 10 && subList.size() == 1) {
                formattedOutput += "| STRIKE";
            }
            // TODO - Michael Simon: 10 ist eine magic number ... daher auslagern
            if (framePoints == 10 && subList.size() > 1) {
                formattedOutput += "| SPARE";
            }
            for (int j = formattedOutput.length(); j < 55; j++) {
                formattedOutput += " ";
            }
            formattedOutput += "| getPoints: " + frameResult.get(i).points;
            System.out.println(formattedOutput);
        }
        System.out.println(SUB_LINE + " getPoints: " + game.summarizePoints());

    }

    /**
     * Displays the dialog options for an active game
     */
    // TODO - Michael Simon: hier sind auch wieder eine Menge "magic numbers" enthalten ... klar ist der Bowling-Kontext nicht so umfangreich, aber in anderen Projekten ist nicht sofort klar, was  1/2/3 usw. bedeuten
    // TODO - Michael Simon: hier hat mir das Exception-Handling auch gut gefallen
    private void showRollDialog() {
        System.out.print(LINE + "(1) Roll Random\n(2) Roll value\n(3) Exit\n->: ");
        try {
            switch (readInt()) {
                case 1:
                    if (!game.getFrameResultOfTheLatestFrame().frameEnded) {
                        game.throwBall(ThreadLocalRandom.current().
                                nextInt(0, 11 - game.getFrameResultOfTheLatestFrame().pinsFallen));
                    } else {
                        game.throwBall(ThreadLocalRandom.current().nextInt(0, 11));
                    }
                    break;
                case 2:
                    System.out.print("Please enter a number: \n");
                    game.throwBall(readInt());
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
                    (10 - game.getFrameResultOfTheLatestFrame().pinsFallen) + " inclusive\n");
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
    // TODO - Michael Simon: der name ist unpassend ... readUserInput bzw. getUserInput wären denkbar
    private int readInt() throws IOException, NumberFormatException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return Integer.parseInt(br.readLine());
    }

    /**
     * Displays dialog options for a completed game
     */
    private void showAfterGameDialog() {
        System.out.print(LINE + "(1) New game\n(2) Exit\n->: ");
        try {
            switch (readInt()) {
                case 1:
                    game = new Game();
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
