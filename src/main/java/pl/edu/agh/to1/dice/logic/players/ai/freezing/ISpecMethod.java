package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface ISpecMethod {
    /**
     * Computes quality coefficient of a given figure, for a given dice configuration.
     *
     * @param figure figure for which we want to get a quality coefficient
     * @param diceBox current dice configuration
     * @return pair containing quality coefficient and a list of indexes to freeze
     */
    Pair<List<Integer>, Double> computeQuality(IFigure figure, DiceBox diceBox);
}
