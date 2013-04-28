package pl.edu.agh.to1.dice;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceGame;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class App {

    @Autowired
    static DiceGame diceGame;

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationConfig.xml");
        String firstUserName = null;
        String secondUserName = null;
        String amountOfPlayers = null;
        final List<Player> players = new ArrayList<Player>();
        try {
            amountOfPlayers = LineInputReader.readSingleLine("Amount of players");
            for (int i = 0; i < Integer.valueOf(amountOfPlayers); i++) {
                players.add(new User(LineInputReader.readSingleLine(i+" . name:")));
            }
        } catch (ReadingUserInputException e) {
            System.out.println("Unfortunately some problems with STDIN occured. Application will be closed.");
            System.exit(1);
        }
        try {
//            new DiceGame(players).play();
            DiceGame diceGame1 = (DiceGame) beanFactory.getBean("diceGame");
            diceGame1.setUsers(players);
            diceGame1.play();

        } catch (ReadingUserInputException e) {
            LOGGER.log(Level.SEVERE, "Reading input problems, application will exit", e);
            System.out.println("Unpredictable problems with input occured, application will exit. We are sorry");
            System.exit(2);
        }
    }
}
