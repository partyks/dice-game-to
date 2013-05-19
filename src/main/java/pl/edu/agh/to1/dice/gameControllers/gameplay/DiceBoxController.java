package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Piotr Turek
 */
@Component
public class DiceBoxController {
    private DiceBox diceBox = new DiceBox(5);

    private List<Dice> frozenDices;

    @PostConstruct
    public void init() {
        reset();
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public Map<String, Dice> getDices() {
        final List<Dice> dices = diceBox.getDices();
        Map<String, Dice> map = new HashMap<>();
        for (Dice d : dices) {
            map.put(String.valueOf(d.getScore()), d);
        }
        return map;
    }

    public List<Dice> getFrozenDices() {
        return diceBox.getFrozenDices();
    }

    public void reset() {
        diceBox.prepare();
        diceBox.roll();
    }

    public void setFrozenDices(List<Dice> frozenDices) {
        this.frozenDices = frozenDices;
    }
}
