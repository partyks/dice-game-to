package pl.edu.agh.to1.dice.TUI;

import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.FreezeIndexesReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pure utility class for reading line from Input (with Spring e.g. could be manage as a bean and
 * constructed with stream). Here, for the simplicity I introduce hardcoded stream.
 * @author Michal Partyka
 */
public class LineInputReader {
    private static final Logger LOGGER = Logger.getLogger(LineInputReader.class.getName());
    private static BufferedReader in = null;

    public static String readSingleLine(String question) throws ReadingUserInputException {
        String userAnswer;
        if ( in == null ) {
            in = new BufferedReader( new InputStreamReader( System.in ) );
        }
        System.out.print(question + ": ");
        try {
            userAnswer = in.readLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unfortunetly reading from given stream have proned some errors", e);
            throw new ReadingUserInputException("Obtaining " + question + " from user caused Exception:", e);
        }
        return userAnswer;
    }

    public static List<Integer> readFreezeIndexes(DiceBox diceBox) throws ReadingUserInputException, FreezeIndexesReadingException {
        String frozeDicesDescription = LineInputReader.readSingleLine("Freeze dices: (Provide number separated with whitespace");
        String[] frozeThatDices = frozeDicesDescription.split("\\s");
        List<Integer> dicesToFrozeIndexes = new ArrayList<Integer>();
        try {
            for (int i = 0; i < frozeThatDices.length; i++) {
                dicesToFrozeIndexes.add(Integer.valueOf(frozeThatDices[i]));
                if (dicesToFrozeIndexes.get(i) < 0 || dicesToFrozeIndexes.get(i) > diceBox.quantity() - 1) {
                    System.out.println("Unfortunetly, number out of range...");
                    throw new FreezeIndexesReadingException("number out of range");
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Given input is not proper, numbers [1..5] required", e);
            throw new FreezeIndexesReadingException("Not proper input format...");
        }
        return dicesToFrozeIndexes;
    }
}
