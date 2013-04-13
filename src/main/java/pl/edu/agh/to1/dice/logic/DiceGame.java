package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;

import java.util.List;

public class DiceGame {
    private final List<User> users;
    private final DiceBox diceBox =  new DiceBox(5);

    public DiceGame(List<User> users) {
        if (users.size() != 2) {
            throw new IllegalStateException("Wrong number of users");
        }
        this.users = users;

    }

    public void play() throws ReadingUserInputException {
        System.out.println("Playing Dice with users: ");
        for (User user : users) {
            System.out.println(user);
        }

        final int roundAmount = Figure.values().length;
        printScore();
        for (int i = 0; i < roundAmount; i++) {
            for (User user : users) {
                System.out.println("Player: " + user + " turn.");
                nextRound(user);
                printScore();
            }
        }
    }

    private void nextRound(User player) throws ReadingUserInputException {
        final int rollingTimes = 2;
        for (int i = 0; i < rollingTimes; i++) {
            diceBox.roll();
            System.out.println(player.getCurrentStock(diceBox));
            System.out.println(diceBox);
            player.freezeDices(diceBox);
        }
        diceBox.roll();
        System.out.println(diceBox);
        System.out.println(player.getCurrentStock(diceBox));
        player.sparePoints(diceBox);
    }

    private void printScore() {
        String defaultStr = "                 |";
        System.out.print("Figure" + defaultStr.substring("Figure".length()));
        for (User user : users) {
            System.out.print(user.getName() + defaultStr.substring(user.getName().length()));
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        for (Figure figure : Figure.values()) {
            System.out.print(figure + defaultStr.substring(figure.toString().length()));
            for (User user : users) {
                System.out.print(user.getScore(figure) + defaultStr.substring(user.getScore(figure).toString().length()));
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
        System.out.println("__RESULT:______");
        System.out.print(defaultStr);
        for (User user : users) {
            System.out.print(user.getResult() + defaultStr.substring(user.getResult().toString().length()));
        }
        System.out.println();
        System.out.println();
    }
}
