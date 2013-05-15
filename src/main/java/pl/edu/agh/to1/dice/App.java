package pl.edu.agh.to1.dice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.figures.FigureManager;
import pl.edu.agh.to1.dice.logic.figures.RandomFigureConfiguration;
import pl.edu.agh.to1.dice.logic.figures.SingleDiceConfigurationFactory;
import pl.edu.agh.to1.dice.logic.figures.TripleDiceConfigurationFactory;
import pl.edu.agh.to1.dice.logic.flow.DiceGame;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.logic.players.ai.BotManager;
import pl.edu.agh.to1.dice.playermodel.UserModel;
import pl.edu.agh.to1.dice.playermodel.UserService;
import pl.edu.agh.to1.dice.ranking.Ranking;
import pl.edu.agh.to1.dice.ranking.SortUsersByWonGames;
import pl.edu.agh.to1.dice.repository.UserAlreadyPersistedInDatabaseException;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * This is some kind of starter class, It could be divided into the class who reads the configuration from user, and
 * the class which build the application (better words are, it should be). Unfortunetly, because of the lack of time
 * it isn't ;-)
 */

//@Component
public class App {
    private static final BeanFactory beanFactory = null;// = new ClassPathXmlApplicationContext("applicationConfig.xml");
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final UserService userService = null;//(UserService) beanFactory.getBean("userService");
    private static final BotManager botManager = null;//(BotManager) beanFactory.getBean("botManager");

    public static void main(String[] args) {
        try {
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
                        diceGame.setPlayers(players);
                        try {
                            diceGame.play();
                        } catch (ReadingUserInputException e) {
                            LOGGER.error("Reading input problems, application will exit", e);
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
        } catch (Exception e) {
            System.out.println("Unfortunetly something bad has happened. Please check if you are in the AGH net or " +
                    "connect via VPN. Unexpected error has occurred, application will be terminated");
            LOGGER.error("What happend?", e);
            System.exit(12141);
        }
    }

    private static void setConfiguration() throws ReadingUserInputException {
        FigureManager figureManager = (FigureManager) beanFactory.getBean("figureManager");
        Integer configuration = LineInputReader.chooseCase("Choose the mode: ",
                new String[]{"Single", "Triple", "Random"});
        switch (configuration) {
            case 0: figureManager.setConfiguration(new SingleDiceConfigurationFactory());
                    break;
            case 1: figureManager.setConfiguration(new TripleDiceConfigurationFactory());
                    break;
            case 2: figureManager.setConfiguration(new RandomFigureConfiguration());
                    break;
        }
    }

    public static List<Player> getPlayersConfiguration() throws ReadingUserInputException {
        String numberOfPlayers = LineInputReader.readSingleLine("How many players will play?");
        String numberOfBots = LineInputReader.readSingleLine("How many bots will play?");

        Integer amountOfPlayers = null;
        Integer amountOfBots = null;
        try {
            amountOfPlayers = Integer.valueOf(numberOfPlayers);
            amountOfBots = Integer.valueOf(numberOfBots);
            if (amountOfPlayers < 0 || amountOfBots < 0 || (amountOfBots == 0 && amountOfPlayers == 0)) {
                throw new IllegalStateException("negative number of players (" + amountOfPlayers + ") " +
                        "or bots (" + amountOfBots + ")");
            }
        } catch (NumberFormatException|IllegalStateException e) {
            LOGGER.error("Number provided by user is negative", e);
            System.out.println("Please provide non-negative number, reading players configuration from the begining..");
            return getPlayersConfiguration();
        }

        final List<Player> players = new ArrayList<>();

        while (amountOfPlayers > 0) {
            if (LineInputReader.yesNo("Choose existing user from database")) {
                UserModel user = chooseUserFromDb();
                players.add(new User(user));
            } else {
                User user = newUser();
                players.add(user);
            }
            amountOfPlayers--;
        }
        if (amountOfBots > 0) {
            Integer botsLevel = LineInputReader.chooseCase("Bots level ", new String[] {"Hard", "Medium", "Low"});
            double botCoef = getCoefByBotsLevel(botsLevel);
            players.addAll(botManager.createBots(amountOfBots, botCoef));
        }

        return players;
    }

    private static User newUser() throws ReadingUserInputException {
        String username = LineInputReader.readSingleLine("Provide username for player: ");
        UserModel userModel = new UserModel(username, new GlobalStatistics(0,0,0));
        try {
            userService.persist(userModel);
        } catch (UserAlreadyPersistedInDatabaseException e) {
            LOGGER.error("Trying to persist user who is already persisted in database", e);
            System.out.println("Unfortunetly, that username is already used, please try another");
            userService.displayAvailableUsers();
            return newUser();
        }
        //attach object (get ID):
        UserModel userModel1 = userService.getUserByUsername(username);
        return new User(userModel1);
    }

    private static double getCoefByBotsLevel(Integer botsLevel) {
        switch (botsLevel) {
            case 1: return 0.78;
            case 2: return 0.70;
        }
        return 0.85;
    }

    private static UserModel chooseUserFromDb() throws ReadingUserInputException {
        UserModel[] users = userService.displayAvailableUsers();
        Integer amountOfAvailableUsers = users.length;
        Integer chosen = null;
        try {
            chosen = Integer.valueOf(LineInputReader.readSingleLine("Choose number:"));
            if (chosen > amountOfAvailableUsers || chosen < 0) {
                throw new IllegalStateException("negative player ...");
            }
        } catch (NumberFormatException|IllegalStateException e) {
            System.out.println("You have provided wrong number, please try again");
            return chooseUserFromDb();
        }
        return users[chosen];
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
