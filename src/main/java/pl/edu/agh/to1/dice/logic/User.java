package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User class, in real system it would be annotated would be an entity
 * @author Michal Partyka
 */
@Entity
public class User implements Player {
    private final String name;

    @Transient
    private final Score score = new Score();
    @Transient
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    public User(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "user " + name;
    }

    public void sparePoints(DiceBox diceBox) throws ReadingUserInputException {
        String figureSignature = LineInputReader.readSingleLine("Choose figure: ");
        try {
            score.add(Figure.valueOf(figureSignature), diceBox);
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

    public Integer getScore(Figure figure) {
        return score.getScore(figure);
    }


    public String getCurrentStock(DiceBox diceBox) {
        return score.currentStock(diceBox);
    }

    public Result getResult() {
        return score.getResult();
    }
}
