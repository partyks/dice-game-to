package pl.edu.agh.to1.dice.logic.players;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.to1.dice.StatisticsModel.GlobalStatistics;
import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.FreezeIndexesReadingException;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;

import javax.persistence.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User class, in real system it would be annotated would be an entity
 * @author Michal Partyka
 */
@Entity
public class User extends AbstractPlayer {
    @GeneratedValue
    @Id
    private Integer Id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private GlobalStatistics globalStatistics;

    @Transient
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    @Transient
    @Autowired
    IFigureManager figureManager;


    public User(){
        super();
    }

    public User(String name) {
        super(name);
    }

    public void sparePoints(DiceBox diceBox) throws ReadingUserInputException {
        String figureSignature = LineInputReader.readSingleLine("Choose figure: ");
        try {
            score.add(figureManager.getFigureBySignature(figureSignature), diceBox);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Given input is not proper, figure.valueOf :(", e);
            System.out.println("Unfortunetly, given input is not proper, please specify correct figure...");
            sparePoints(diceBox);
        } catch (IllegalStateException e) {
            LOGGER.log(Level.WARNING, "Given figure by user was already filled in score");
            System.out.println("This figure was filled, please choose another one...");
            sparePoints(diceBox);
        }
    }

    public void manageDices(DiceBox diceBox) throws ReadingUserInputException {
        int failures=0;
        try {
            diceBox.freeze(LineInputReader.readFreezeIndexes(diceBox));
        } catch (FreezeIndexesReadingException e) {
            failures++;
            if (failures < 5) {
                manageDices(diceBox);
            } else {
                throw new ReadingUserInputException("Reading freeze indexes wasn't possible!");
            }
        }
    }

    public GlobalStatistics getGlobalStatistics() {
        return globalStatistics;
    }

    public void setGlobalStatistics(GlobalStatistics globalStatistics) {
        this.globalStatistics = globalStatistics;
    }
}
