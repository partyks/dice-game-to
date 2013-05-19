package pl.edu.agh.to1.dice.gameControllers;

import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.players.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
public class DisplayingResultController {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void sortPlayers() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getResult().result(), o1.getResult().result());
            }
        });
    }
}
