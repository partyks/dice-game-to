package pl.edu.agh.to1.dice;

import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceGame;
import pl.edu.agh.to1.dice.logic.Player;
import pl.edu.agh.to1.dice.logic.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String firstUserName = null;
        String secondUserName = null;
        String amountOfPlayers = null;
        final List<Player> players = new ArrayList<Player>();
        try {
            amountOfPlayers = LineInputReader.readSingleLine("Amount of players");
            for (int i = 0; i < Integer.valueOf(amountOfPlayers); i++) {
                players.add(new User(LineInputReader.readSingleLine(i+" . name:")));
            }
        } catch (ReadingUserInputException e) {
            System.out.println("Unfortunately some problems with STDIN occured. Application will be closed.");
            System.exit(1);
        }
        try {
            new DiceGame(players).play();
        } catch (ReadingUserInputException e) {
            LOGGER.log(Level.SEVERE, "Reading input problems, application will exit", e);
            System.out.println("Unpredictable problems with input occured, application will exit. We are sorry");
            System.exit(2);
        }
    }
}
