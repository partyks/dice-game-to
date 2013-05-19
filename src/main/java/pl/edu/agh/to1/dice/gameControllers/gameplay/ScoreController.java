package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.players.Score;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Controller
public class ScoreController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    private List<Score> scores;

    @PostConstruct
    public void init() {
    this.scores = Arrays.asList((Score) applicationContext.getBean("score"),
            (Score) applicationContext.getBean("score"));
    }

//    private List<Player> players = Arrays.asList((Player) new User(new UserModel("Player1", new GlobalStatistics(0,0,0))),
//              new User(new UserModel("Player2", new GlobalStatistics(0,0,0))));


//    public List<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(List<Player> players) {
//        this.players = players;
//    }
//
    public Integer getAmountOfPlayers() {
//        return players.size()+1;
        return scores.size()+1;
    }


    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
