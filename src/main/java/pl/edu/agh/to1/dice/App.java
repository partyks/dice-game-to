package pl.edu.agh.to1.dice;

import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceGame;
import pl.edu.agh.to1.dice.logic.User;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String firstUserName = null;
        String secondUserName = null;
        try {
            firstUserName = LineInputReader.readSingleLine("User1 name");
            secondUserName = LineInputReader.readSingleLine("User2 name");
        } catch (ReadingUserInputException e) {
            System.out.println("Unfortunately some problems with STDIN occured. Application will be closed.");
            System.exit(1);
        }
        try {
            new DiceGame(Arrays.asList(new User(firstUserName), new User(secondUserName))).play();
        } catch (ReadingUserInputException e) {
            LOGGER.log(Level.SEVERE, "Reading input problems, application will exit", e);
            System.out.println("Unpredictable problems with input occured, application will exit. We are sorry");
            System.exit(2);
        }
    }
}
