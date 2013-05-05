package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
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
@Service("FULLSpecMethod")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class FullSpecMethod extends AbstractSpecMethod {

    private int candid3 = 3;
    private int candid2 = 2;

    @Override
    protected Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze) {
        List<Integer> valueCounts = new LinkedList<>();
        BotDiceBox probable = new BotDiceBox(diceBox.quantity());
        final List<Dice> dices = probable.getDices();
        int i;
        for (i = 0; i < 3; i++) {
            ((BotDice)dices.get(i)).setScore(candid3);
        }
        for (; i < probable.quantity(); i++) {
            ((BotDice)dices.get(i)).setScore(candid2);
        }
        i = probable.quantity() - toFreeze.size();
        while (i-- > 0) {
            valueCounts.add(1);
        }

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
                final int distL = 3 - lhs.getRight();
                final int distR = 3 - rhs.getRight();

                if (distL > 0 && distR <= 0) {
                    return 1;
                }
                if (distR > 0 && distL <= 0) {
                    return -1;
                }

                return distL - distR;
            }
        });

        final Pair<Integer, Integer> candid3 = valueToFreq.get(0);
        final Pair<Integer, Integer> candid2 = valueToFreq.get(1);
        int candid3left = Math.min(candid3.getRight(), 3);
        List<Integer> toFreeze = new LinkedList<>();
        int id = 0;
        for (Dice dice : diceBox) {
            final int diceScore = dice.getScore();
            if (diceScore == candid3.getLeft() && candid3left > 0) {
                toFreeze.add(id);
                candid3left--;
            } else if (diceScore == candid2.getLeft()) {
                toFreeze.add(id);
            }
            id++;
        }

        this.candid2 = candid2.getLeft();
        this.candid3 = candid3.getLeft();

        return toFreeze;
    }

}
