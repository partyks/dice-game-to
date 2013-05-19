package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.Score;
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

    private List<Score> scores;
    private List<Player> players;

    @PostConstruct
    public void init() {
        this.scores = Arrays.asList((Score) applicationContext.getBean("score"),
            (Score) applicationContext.getBean("score"));
        this.players = Arrays.asList((Player) UserFactory.newInstance(new UserModel("Player1", new GlobalStatistics(0,0,0)))
            , (Player) UserFactory.newInstance(new UserModel("Player2", new GlobalStatistics(0,0,0))));
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

}
