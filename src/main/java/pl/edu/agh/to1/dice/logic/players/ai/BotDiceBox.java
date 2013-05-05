package pl.edu.agh.to1.dice.logic.players.ai;

import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public class BotDiceBox extends DiceBox {
    public BotDiceBox(int amount) {
        super(0);
        for (int i = 0; i < amount; i++) {
            dices.add(new BotDice());
        }
    }

    public BotDiceBox(DiceBox toClone) {
        super(0);
        for (Dice d : toClone) {
            BotDice botDice = new BotDice();
            botDice.setScore(d.getScore());
            dices.add(botDice);
        }
    }

    public List<Dice> getDices() {
        return dices;
    }
}
