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
    private final List<ViewDice> dices = new ArrayList<>(5);
    private final List<ViewDice> frozenDices = new ArrayList<>(5);

    @PostConstruct
    public void init() {
        reset();
        for (Dice dice : diceBox) {
            dices.add(new ViewDice(dice));
        }
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public List<ViewDice> getDices() {
        return dices;
    }

    public void reset() {
        diceBox.prepare();
        diceBox.roll();
        frozenDices.clear();
    }

    public class ViewDice {
        private final Dice dice;

        public ViewDice(Dice dice) {
            this.dice = dice;
        }

        public Integer getScore() {
            return dice.getScore();
        }

        public Boolean isFrozen() {
            return frozenDices.contains(dice);
        }

        public void setFrozen(Boolean frozen) {
            if (!isFrozen()) {
                frozenDices.add(this);
            }
        }
    }
}
