package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.Score;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface IFreezingStrategy {

    /**
     * Given current score and dice configuration, returns indexes to freeze.
     * @param score
     * @param diceBox
     * @return indexes to freeze, starting with 0
     */
    List<Integer> getToFreeze(Score score, DiceBox diceBox);
}
