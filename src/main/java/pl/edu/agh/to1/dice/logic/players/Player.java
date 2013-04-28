package pl.edu.agh.to1.dice.logic.players;

import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.Result;

/**
 * @author Michal Partyka
 */
//TODO: extract all methods bar sparePoints and manageDices to a separate interface of even better, to an abstract class
public interface Player {
    void sparePoints(DiceBox diceBox) throws ReadingUserInputException;
    void manageDices(DiceBox diceBox) throws ReadingUserInputException;
    String getCurrentStock(DiceBox diceBox);
    String getName();
    Integer getScore(IFigure figure);
    Result getResult();
}
