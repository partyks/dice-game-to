package pl.edu.agh.to1.dice.logic.players;

import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.Figure;
import pl.edu.agh.to1.dice.logic.Result;

/**
 * @author Michal Partyka
 */
public interface Player {
    void sparePoints(DiceBox diceBox) throws ReadingUserInputException;
    void manageDices(DiceBox diceBox) throws ReadingUserInputException;
    String getCurrentStock(DiceBox diceBox);
    String getName();
    Integer getScore(Figure figure);
    Result getResult();
}
