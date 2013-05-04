package pl.edu.agh.to1.dice;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;
import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.figures.FigureManager;
import pl.edu.agh.to1.dice.logic.figures.TripleDiceConfigurationFactory;
import pl.edu.agh.to1.dice.logic.flow.DiceGame;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.playermodel.UserModel;
import pl.edu.agh.to1.dice.ranking.Ranking;
import pl.edu.agh.to1.dice.ranking.SortUsersByWonGames;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class App {
    private static final BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationConfig.xml");
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        DiceGame diceGame = (DiceGame) beanFactory.getBean("diceGame");
        String[] whatToDo = {"Play Game", "View Ranking", "Quit"};
        int i=-1;
        while(i != 2) {
            try {
                i = LineInputReader.chooseCase("What to do", whatToDo);
            } catch (ReadingUserInputException e) {
                System.out.println("I am so sorry, I didn't catch it, could you repeat?");
            }
            switch (i) {
                case 0:
                    setConfiguration();
                    List<Player> players = getPlayersConfiguration();
                    diceGame.setUsers(players);
                    try {
                        diceGame.play();
                    } catch (ReadingUserInputException e) {
                        LOGGER.log(Level.SEVERE, "Reading input problems, application will exit", e);
                        System.out.println("Unpredictable problems with input occured, application will exit. " +
                                "We are sorry");
                        System.exit(2);
                    }
                    break;
                case 1:
                    Ranking ranking = (Ranking) beanFactory.getBean("ranking");
                    ranking.sort(new SortUsersByWonGames());
                    ranking.displayRanking();
                    break;
            }
        }
    }

    private static void setConfiguration() {
        //TODO: asking for configuration goes here
        FigureManager figureManager = (FigureManager) beanFactory.getBean("figureManager");
        figureManager.setConfiguration(new TripleDiceConfigurationFactory());
    }

    public static List<Player> getPlayersConfiguration() {
        //TODO: asking user for the player configuration goes here
        return Arrays.asList((Player) new User(new UserModel("asdf", new GlobalStatistics(0,0,0))),
                            (Player) new User(new UserModel("asdqas", new GlobalStatistics(0,0,0))));
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
