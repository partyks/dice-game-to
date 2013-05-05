package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.ai.BotDice;
import pl.edu.agh.to1.dice.logic.players.ai.BotDiceBox;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.List;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractSpecMethod implements ISpecMethod {
    @Override
    public Pair<List<Integer>, Double> computeQuality(IFigure figure, DiceBox diceBox) {
        final List<Integer> toFreeze = getIndexesToFreeze(figure, diceBox);
        final Pair<List<Integer>, Double> countsAndScore = getValueCountsAndScore(figure, diceBox, toFreeze);
        final List<Integer> possibleValueCounts = countsAndScore.getLeft();
        final double possibleScore = countsAndScore.getRight();

        double probability = 1.;
        int nonZero = 0;
        for (Integer count : possibleValueCounts) {
            if (count != 0) {
                nonZero++;
                probability *= (double) count / 6.;
            }
        }
        probability *= (double) factorial(nonZero);

        return new Pair<List<Integer>, Double>(toFreeze, possibleScore * probability);
    }

    private int factorial(int n) {
        int ret = 1;
        for (int i = 1; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }

    protected void mostProbableFill(BotDiceBox diceBox, List<Integer> valueCounts, int start) {
        int i = start;
        List<Dice> dices = diceBox.getDices();
        int freeToRoll = dices.size() - start;
        int j = 0;
        while (j < freeToRoll/2) {
            ((BotDice)dices.get(i++)).setScore(6);
            j++;
        }
        while (i < dices.size()) {
            ((BotDice)dices.get(i++)).setScore(1);
        }
        if (freeToRoll % 2 != 0) {
            ((BotDice)dices.get(dices.size()-1)).setScore((int)3.5 * freeToRoll - diceBox.sum() + 1);
        }
        for (i = 0; i < freeToRoll/2; i++) valueCounts.add(6);
        for (i = freeToRoll/2; i < freeToRoll; i++) valueCounts.add(1);
    }


    protected abstract Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze);

    protected abstract List<Integer> getIndexesToFreeze(IFigure figure, DiceBox diceBox);
}
