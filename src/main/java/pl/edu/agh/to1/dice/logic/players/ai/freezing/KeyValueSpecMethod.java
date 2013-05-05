package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.ai.BotDice;
import pl.edu.agh.to1.dice.logic.players.ai.BotDiceBox;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public class KeyValueSpecMethod extends AbstractSpecMethod {

    List<Integer> keyValues = new LinkedList<>();

    @Override
    protected Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze) {
        List<Integer> valueCounts = new LinkedList<Integer>();

        BotDiceBox probable = new BotDiceBox(diceBox.quantity());
        BotDiceBox curDiceBox = new BotDiceBox(diceBox);

        List<Dice> curDices = curDiceBox.getDices();
        List<Dice> dices = probable.getDices();


        int i;
        for (i = 0; i < toFreeze.size(); i++) {
            final int toSet = curDices.get(toFreeze.get(i)).getScore();
            ((BotDice)dices.get(i)).setScore(toSet);
        }

        if (i < dices.size()) {
            BotDice improved = (BotDice) dices.get(i);
            improved.setScore(Collections.max(keyValues));
            valueCounts.add(dices.size() - i);
            i++;
        }

        int filler = 1;
        while (filler < 6 && keyValues.contains(filler)) filler++;

        for (; i < dices.size(); i++) {
            ((BotDice)dices.get(i)).setScore(filler);
        }

        return new Pair<List<Integer>, Double>(valueCounts, (double) figure.getScore(probable));
    }

    @Override
    protected List<Integer> getIndexesToFreeze(IFigure figure, DiceBox diceBox) {
        final List<Integer> toFreeze = new LinkedList<Integer>();
        int id = 0;
        for (Dice dice : diceBox) {
            if (keyValues.contains(dice.getScore())) {
                toFreeze.add(id);
            }
            id++;
        }
        return toFreeze;
    }

    public List<Integer> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(List<Integer> keyValues) {
        this.keyValues = keyValues;
    }

}
