package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.gameControllers.gameplay.ScoreController;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Controller
@Scope("session")
public class WebFlowController {
    @Autowired
    private IFigureManager figureManager;

    @Autowired
    private ScoreController scoreController;

    private Integer roundsPlayed = 0;
    private int currentPlayerId = 0;

    public Player getActivePlayer() {
        final List<Player> players = scoreController.getPlayers();
        return players.get(currentPlayerId);
    }

    public Boolean mustChooseFigure() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Boolean canRoll() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Boolean hasFinished() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getRoundsPlayed() {
        return roundsPlayed;
    }

    public boolean isPlayerRound(Player player) {
        return player.getName().
                equals(getActivePlayer().getName());
    }

    public void reset() {
        roundsPlayed = 0;
    }

}
