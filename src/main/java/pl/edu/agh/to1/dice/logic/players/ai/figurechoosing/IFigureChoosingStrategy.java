package pl.edu.agh.to1.dice.logic.players.ai.figurechoosing;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.Score;

/**
 * Author: Piotr Turek
 */
public interface IFigureChoosingStrategy {
    /**
     * Given current score and dice configuration, returns which figure to choose
     * @param score
     * @param diceBox
     * @return figure to choose
     */
    IFigure chooseFigure(Score score, DiceBox diceBox);
}
