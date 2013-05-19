package pl.edu.agh.to1.dice.logic.players;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to1.dice.App;
import pl.edu.agh.to1.dice.TUI.LineInputReader;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.dices.FreezeIndexesReadingException;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.playermodel.UserModel;


/**
 * User class, in real system it would be annotated would be an entity
 * @author Michal Partyka
 */
public class User extends AbstractPlayer {
    private final UserModel userModel;

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private IFigureManager figureManager;


    public User(UserModel userInfo) {
        super(userInfo.getName());
        this.userModel = userInfo;
        figureManager = (IFigureManager) App.getBeanFactory().getBean("figureManager");
    }

    public User(UserModel userModel, Score score1, IFigureManager figureManager) {
        super(score1, userModel.getName());
        this.userModel = userModel;
        this.figureManager = figureManager;
    }



    public void sparePoints(DiceBox diceBox) throws ReadingUserInputException {
        String figureSignature = LineInputReader.readSingleLine("Choose figure: ");
        try {
            score.add(figureManager.getFigureByName(figureSignature), diceBox);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Given input is not proper, figure.valueOf :(", e);
            System.out.println("Unfortunetly, given input is not proper, please specify correct figure...");
            sparePoints(diceBox);
        } catch (IllegalStateException e) {
            LOGGER.warn("Given figure by user was already filled in score");
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

    public UserModel getUserModel() {
        return userModel;
    }
}
