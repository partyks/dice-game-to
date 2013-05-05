package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.ai.BotDice;
import pl.edu.agh.to1.dice.logic.players.ai.BotDiceBox;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public class EqualsSpecMethod extends AbstractSpecMethod {

    private final Integer targetFrequency;

    public EqualsSpecMethod(Integer targetFrequency) {
        this.targetFrequency = targetFrequency;
    }

    @Override
    protected Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze) {
        List<Integer> valueCounts = new LinkedList<>();

        BotDiceBox probable = new BotDiceBox(diceBox.quantity());
        BotDiceBox curDiceBox = new BotDiceBox(diceBox);

        List<Dice> curDices = curDiceBox.getDices();
        List<Dice> dices = probable.getDices();

        int i;
        final int toSet = curDices.get(toFreeze.get(0)).getScore();
        for (i = 0; i < toFreeze.size(); i++) {
            ((BotDice)dices.get(i)).setScore(toSet);
        }
        for (; i < targetFrequency; i++) {
            ((BotDice)dices.get(i)).setScore(toSet);
            valueCounts.add(1);
        }

        mostProbableFill(probable, valueCounts, i);

        return new Pair<List<Integer>, Double>(valueCounts, (double) figure.getScore(probable));
    }

    @Override
    protected List<Integer> getIndexesToFreeze(IFigure figure, DiceBox diceBox) {
        List<Pair<Integer, Integer>> valueToFreq = new LinkedList<>();

        final int[] mapCount = diceBox.getMapCount();

        for (int i = 1; i < mapCount.length; i++) {
            valueToFreq.add(new Pair<Integer, Integer>(i, mapCount[i]));
        }

        Collections.sort(valueToFreq, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {
                final int diff = rhs.getRight() - lhs.getRight();
                if (diff < 0) { //left > right
                    return lhs.getRight() <= targetFrequency ? diff : -diff;
                } else if (diff > 0) { //right >= left
                    return rhs.getRight() <= targetFrequency ? diff : -diff;
                }
                //both have equal frequency so we compare their values
                return rhs.getLeft() - lhs.getLeft();
            }
        });

        int bestChoice = valueToFreq.get(0).getLeft();
        List<Integer> toFreeze = new LinkedList<>();
        int id = 0;
        for (Dice dice : diceBox) {
            if (dice.getScore() == id) {
                toFreeze.add(id);
            }
            id++;
        }

        return toFreeze;
    }

    public Integer getTargetFrequency() {
        return targetFrequency;
    }
}
