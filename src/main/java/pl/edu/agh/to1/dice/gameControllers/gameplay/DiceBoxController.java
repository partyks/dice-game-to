package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Author: Piotr Turek
 */
@Component
public class DiceBoxController {
    private DiceBox diceBox = new DiceBox(5);

    private List<Dice> frozenDices;

    private Integer rollsLeft = 2;

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
        frozenDices = new ArrayList<>(5);
        frozenDices.addAll(frozenDicesBox);
        Map<String, Dice> map = new TreeMap<>();
        int id = 1;
        for (Dice d : dices) {
            map.put(id + ": " + String.valueOf(d.getScore()), d);
            id++;
        }
        return map;
    }

    public List<Dice> getFrozenDices() {
        return frozenDices;
    }

    public void reset() {
        diceBox.roll();
        rollsLeft = 2;
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

    public void rollRequested(FacesContext facesContext) {
        rollsLeft--;
        assert(frozenDices.size() == 3);
        for (Dice fd : frozenDices) {
            assert(diceBox.setFreeze(fd) == true);
        }
        diceBox.roll();
    }

}
