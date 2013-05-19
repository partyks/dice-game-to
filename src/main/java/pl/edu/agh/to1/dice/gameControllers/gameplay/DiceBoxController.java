package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Component
public class DiceBoxController {
    private DiceBox diceBox = new DiceBox(5);

    @PostConstruct
    public void init() {
        reset();
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public List<Dice> getDices() {
        return diceBox.getDices();
    }

    public List<Dice> getFrozenDices() {
        return diceBox.getFrozenDices();
    }

    public void reset() {
        diceBox.prepare();
        diceBox.roll();
    }

}
