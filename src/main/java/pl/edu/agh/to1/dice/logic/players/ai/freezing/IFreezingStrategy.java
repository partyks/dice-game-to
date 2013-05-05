package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.players.Score;

import java.util.List;
import java.util.Set;

/**
 * Author: Piotr Turek
 */
public interface IFreezingStrategy {

    /**
     * Given current score and dice configuration, returns indexes to freeze.
     *
     * @param score
     * @param diceBox
     * @return indexes to freeze, starting with 0
     */
    Set<Integer> getToFreeze(Score score, DiceBox diceBox);
}
