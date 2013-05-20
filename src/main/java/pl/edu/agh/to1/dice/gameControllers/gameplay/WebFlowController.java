package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.ai.ModularBot;

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
    private DiceBoxController diceBoxController;

    private Integer roundsPlayed = 0;
    private int currentPlayerId = 0;

    private Boolean finished = false;

    private List<Player> players;

    public Player getActivePlayer() {
        return players.get(currentPlayerId);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPlayersCount() {
        return players.size();
    }

    public Integer getAmountOfPlayers() {
        return players.size()+1;
    }

    public Boolean isFinished() {
        return finished;
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
        currentPlayerId = 0;
        finished = false;
    }

    public void remoteMove() {
        Player player = players.get(currentPlayerId);
        if (!finished && player instanceof ModularBot) {
            diceBoxController.reset();
            final DiceBox diceBox = diceBoxController.getDiceBox();
            diceBox.roll();
            while (diceBoxController.getRollsLeft() > 0) {
                try {
                    player.manageDices(diceBox);
                } catch (ReadingUserInputException e) {

                }
                diceBoxController.rollRequested();
            }
            diceBox.roll();
            try {
                player.sparePoints(diceBox);
            } catch (ReadingUserInputException e) {
            }
            RequestContext.getCurrentInstance().update(":mainForm :dialogForm");
            playerMoved();
        }
    }

    public void playerMoved() {
        currentPlayerId++;
        diceBoxController.reset();
        if (currentPlayerId == players.size()) {
            roundsPlayed++;
            if (roundsPlayed == figureManager.values().size()) {
                finished = true;
                currentPlayerId--;
            } else {
                currentPlayerId = 0;
            }
        }

        remoteMove();
    }

}
