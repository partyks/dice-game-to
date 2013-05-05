package pl.edu.agh.to1.dice.TUI;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.dices.FreezeIndexesReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
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

    public static boolean yesNo(String question) throws ReadingUserInputException {
        return "Y".equalsIgnoreCase(readSingleLine(question + "? (Y/N)"));
    }

    public static Set<Integer> readFreezeIndexes(DiceBox diceBox) throws ReadingUserInputException, FreezeIndexesReadingException {
        String frozeDicesDescription = LineInputReader.readSingleLine("Freeze dices: (Provide number separated with whitespace");
        String[] frozeThatDices = frozeDicesDescription.split("\\s");
        Set<Integer> dicesToFrozeIndexes = new HashSet<>();

        if ("".equals(frozeDicesDescription)) {
            return dicesToFrozeIndexes;
        }

        try {
            for (String frozeThatDice : frozeThatDices) {
                Integer valueToFreeze = Integer.valueOf(frozeThatDice);
                if (valueToFreeze < 0 || valueToFreeze > diceBox.quantity() - 1) {
                    System.out.println("Unfortunetly, number out of range...");
                    throw new FreezeIndexesReadingException("number out of range");
                }
                dicesToFrozeIndexes.add(valueToFreeze);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Given input is not proper, numbers [0..4] required", e);
            throw new FreezeIndexesReadingException("Not proper input format...");
        }
        return dicesToFrozeIndexes;
    }

    /**
     * Gives list of oportunities to the user
     * @param askFor global question
     * @param supported list of possible answers
     * @return number of answer (index of supported array)
     */
    public static int chooseCase(String askFor, String[] supported) throws ReadingUserInputException {
        if (in == null) {
            in = new BufferedReader( new InputStreamReader( System.in));
        }
        System.out.println(askFor);
        System.out.println("The first (0.) is default and will be set if wrong number will be provided:");
        for (int i = 0; i < supported.length; i++) {
            System.out.println(i+". " + supported[i]);
        }
        System.out.print("Choosen number: ");
        int choosen = 0;
        try {
            choosen = Integer.valueOf(in.readLine());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException while reading data from System.in");
            throw new ReadingUserInputException(e); //catched in TUI, - saving for recovery there
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Provided number doesn't match format, please try again");
            return chooseCase(askFor, supported);
        }

        if(choosen >= supported.length || choosen < 0) {
            return 0;
        }
        return choosen;
    }
}
