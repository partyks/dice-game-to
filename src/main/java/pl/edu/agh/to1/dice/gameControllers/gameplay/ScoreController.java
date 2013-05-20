package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.Player;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
public class ScoreController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DiceBoxController diceBoxController;

    @Autowired
    private WebFlowController webFlowController;

    private List<Player> players;

    public String getStock(IFigure figure) {
        final DiceBox diceBox = diceBoxController.getDiceBox();
        return figure.getScore(diceBox).toString();
    }

    public Integer getAmountOfPlayers() {
        return players.size()+1;
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

    public void submitScore(Player user, IFigure figure) {
        user.getScore().add(figure, diceBoxController.getDiceBox());
        webFlowController.playerMoved();
    }

}
