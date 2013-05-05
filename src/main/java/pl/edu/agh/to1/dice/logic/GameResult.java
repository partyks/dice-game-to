package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.logic.players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class GameResult {
    private final List<Player> players;

    public GameResult(List<Player> users) {
        this.players = users;
        Collections.sort(this.players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getResult().result(), o1.getResult().result());
            }
        });
    }

    public List<Player> getWinners() {
        final List<Player> winners = new ArrayList<>();
        winners.add(players.get(0));

        int i = 1;
        while( i < players.size()-1 && players.get(i).getResult().result().equals(players.get(0).getResult().result())) {
            winners.add(players.get(i));
        }

        return winners;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
