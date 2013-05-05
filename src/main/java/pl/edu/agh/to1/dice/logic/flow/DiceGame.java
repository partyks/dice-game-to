package pl.edu.agh.to1.dice.logic.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.GameResult;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.repository.IUserDAO;
import pl.edu.agh.to1.dice.statistics.IStatisticService;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GameInfo;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michał Partyka
 * Class provides an implementation for the game flow and result displaying
 */
@Component
@Entity
public class DiceGame extends AbstractGame {
    @Transient
    @Autowired
    private DiceBox diceBox;

    @Transient
    @Autowired
    private IFigureManager figureManager;

    @Transient
    private List<Player> players;

    @Autowired
    @Transient
    private IUserDAO userDAO;

    @Transient
    @Autowired
    private IStatisticService statisticService;

    public DiceGame() {
        super(new GameInfo("DiceGame", "None", 4));
    }

    public void play() throws ReadingUserInputException {
        System.out.println("Playing Dice with players: ");
        for (Player user : players) {
            System.out.println(user);
        }

        final int roundAmount = figureManager.values().size();
        printScore();
        for (int i = 0; i < roundAmount; i++) {
            for (Player user : players) {
                System.out.println("Player: " + user + " turn.");
                nextRound(user);
                printScore();
            }
        }
        //Print result
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getResult().result(), o1.getResult().result());
            }
        });
        int position = 1;
        for (Player user : players) {
            System.out.println(position + ". " + user);
            position++;
        }

        GameResult gameResult = new GameResult(players);
        statisticService.updateStatistics(gameResult);
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
        for (Player user : players) {
            System.out.print(user.getName() + defaultStr.substring(user.getName().length()));
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        for (IFigure figure : figureManager.values()) {
            System.out.print(figure + defaultStr.substring(figure.toString().length()));
            for (Player user : players) {
                System.out.print(user.getScore(figure) + defaultStr.substring(user.getScore(figure).toString().length()));
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
        System.out.println("___RESULTS___:");
        for (Player user : players) {
            System.out.println(user.getName() + "score:");
            System.out.print(user.getResult());
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void setPlayers(List<Player> players) {
        if(players.size() > 4) {
            throw new IllegalStateException("Maximum 4 players can play dices!");
        }
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
