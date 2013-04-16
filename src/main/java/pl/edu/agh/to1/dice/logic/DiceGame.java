package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michał Partyka
 * Class provides an implementation for the game flow and result displaying
 */
public class DiceGame {
    private final List<Player> users;
    private final DiceBox diceBox =  new DiceBox(5);

    public DiceGame(List<Player> users) {
        this.users = users;
    }

    public void play() throws ReadingUserInputException {
        System.out.println("Playing Dice with users: ");
        for (Player user : users) {
            System.out.println(user);
        }

        final int roundAmount = Figure.values().length;
        printScore();
        for (int i = 0; i < roundAmount; i++) {
            for (Player user : users) {
                System.out.println("Player: " + user + " turn.");
                nextRound(user);
                printScore();
            }
        }


        //Print result
        Collections.sort(users, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getResult().result(), o1.getResult().result());
            }
        });
        int position = 1;
        for (Player user : users) {
            System.out.println(position + ". " + user);
            position++;
        }
    }

    private void nextRound(Player player) throws ReadingUserInputException {
        final int rollingTimes = 2;
        for (int i = 0; i < rollingTimes; i++) {
            diceBox.roll();
            System.out.println(player.getCurrentStock(diceBox));
            System.out.println(diceBox);
            player.manageDices(diceBox);
        }
        diceBox.roll();
        System.out.println(diceBox);
        System.out.println(player.getCurrentStock(diceBox));
        player.sparePoints(diceBox);
    }

    private void printScore() {
        String defaultStr = "                 |";
        System.out.print("Figure" + defaultStr.substring("Figure".length()));
        for (Player user : users) {
            System.out.print(user.getName() + defaultStr.substring(user.getName().length()));
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        for (Figure figure : Figure.values()) {
            System.out.print(figure + defaultStr.substring(figure.toString().length()));
            for (Player user : users) {
                System.out.print(user.getScore(figure) + defaultStr.substring(user.getScore(figure).toString().length()));
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
        System.out.println("__RESULT:______");
        System.out.print(defaultStr);
        for (Player user : users) {
            System.out.print(user.getResult() + defaultStr.substring(user.getResult().toString().length()));
        }
        System.out.println();
        System.out.println();
    }
}
