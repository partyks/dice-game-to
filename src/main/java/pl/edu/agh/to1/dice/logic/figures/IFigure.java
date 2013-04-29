package pl.edu.agh.to1.dice.logic.figures;

import pl.edu.agh.to1.dice.logic.DiceBox;

/**
 * @author: Michal Partyka
 */
public interface IFigure {
    Integer getScore(DiceBox diceBox);
    String getSignature();
}
