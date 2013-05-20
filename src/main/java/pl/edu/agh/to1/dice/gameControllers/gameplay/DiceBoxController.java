package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Author: Piotr Turek
 */
@Component
@Scope("session")
public class DiceBoxController {
    private DiceBox diceBox = new DiceBox(5);

    private List<Dice> frozenDices = new ArrayList<>();

    private Integer rollsLeft = 2;

    private Map<String, Dice> previousDices;

    @PostConstruct
    public void init() {
        reset();
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public Map<String, Dice> getDices() {
        final List<Dice> dices = diceBox.getDices();
        final List<Dice> frozenDicesBox = diceBox.getFrozenDices();
        dices.addAll(frozenDicesBox);
        Map<String, Dice> map = new TreeMap<>();
        int id = 1;
        for (Dice d : dices) {
            map.put(id + ": " + String.valueOf(d.getScore()), d);
            id++;
        }
        this.previousDices = map;
        return map;
    }

    public Map<String, Dice> getPreviousDices() {
        return previousDices;
    }

    public List<Dice> getFrozenDices() {
        return frozenDices;
    }

    public void reset() {
        diceBox.prepare();
        diceBox.roll();
        rollsLeft = 2;
        frozenDices.clear();
    }

    public void setFrozenDices(List<Dice> frozenDices) {
        this.frozenDices = frozenDices;
    }

    public Boolean canRoll() {
        return rollsLeft > 0;
    }

    public Boolean mustChooseFigure() {
        return !canRoll();
    }

    public Integer getRollsLeft() {
        return rollsLeft;
    }

    public void rollRequested() {
        rollsLeft--;
        if (frozenDices != null) {
            for (Dice fd : frozenDices) {
                diceBox.setFreeze(fd);
            }
        }
        diceBox.roll();
    }

}
