package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.Figure;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.Score;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.logic.players.UserFactory;
import pl.edu.agh.to1.dice.playermodel.UserModel;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

import javax.annotation.PostConstruct;
import java.util.Arrays;
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

    private List<Score> scores;
    private List<Player> players;

    @PostConstruct
    public void init() {
        this.scores = Arrays.asList((Score) applicationContext.getBean("score"),
            (Score) applicationContext.getBean("score"));
        this.players = Arrays.asList((Player) UserFactory.newInstance(new UserModel("Player1", new GlobalStatistics(0,0,0)))
            , (Player) UserFactory.newInstance(new UserModel("Player2", new GlobalStatistics(0,0,0))));
        final DiceBox diceBox = diceBoxController.getDiceBox();
        diceBox.roll();
        players.get(0).getScore().add(Figure.ONES,  diceBox);
        players.get(0).getScore().add(Figure.THREE_EQUALS, diceBox);
    }

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

    public void submitScore(User user, IFigure figure) {
        System.out.println("User: " + user.getName());
        System.out.println("Figure: " + figure.getSignature());
    }

}
