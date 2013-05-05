package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.players.ai.BotDice;
import pl.edu.agh.to1.dice.logic.players.ai.BotDiceBox;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.*;

/**
 * Author: Piotr Turek
 */
public class RequestMappingSpecMethod extends AbstractSpecMethod {

    private List<int[]> requestedMappings;
    private int activeChoice;

    @Override
    protected Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze) {
        final List<Integer> valueCounts = new LinkedList<>();
        int freeToRoll = diceBox.quantity() - toFreeze.size();
        for (int i = 0; i < freeToRoll; i++) {
            valueCounts.add(1);
        }
        BotDiceBox probable = new BotDiceBox(diceBox.quantity());
        final List<Dice> dices = probable.getDices();

        final int[] goalMapping = requestedMappings.get(activeChoice);
        int diceID = 0, value = 0;
        for (int freq : goalMapping) {
            for (int j = 0; j < freq; j++) {
                ((BotDice)dices.get(diceID)).setScore(value);
                diceID++;
            }
            value++;
        }

        return new Pair<List<Integer>, Double>(valueCounts, (double) figure.getScore(probable));
    }

    @Override
    protected List<Integer> getIndexesToFreeze(IFigure figure, DiceBox diceBox) {
        final int[] mapping = diceBox.getMapCount();
        final List<Pair<Integer, Integer>> choices = new LinkedList<>();

        int i = 0;
        for (int[] reqMapping : requestedMappings) {
            choices.add(new Pair<Integer, Integer>(i, computeDistance(mapping, reqMapping)));
            i++;
        }

        Collections.sort(choices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {
                return lhs.getRight() - rhs.getRight();
            }
        });

        final Integer choice = choices.get(0).getLeft();
        activeChoice = choice;

        final int[] origGoalMapping = requestedMappings.get(choice);
        int[] goalMapping = Arrays.copyOf(origGoalMapping, origGoalMapping.length);

        final List<Integer> toFreeze = new LinkedList<>();

        i = 0;
        for (Dice dice : diceBox) {
            final int diceScore = dice.getScore();
            if (goalMapping[diceScore] > 0) {
                goalMapping[diceScore]--;
                toFreeze.add(i);
            }
            i++;
        }

        return toFreeze;
    }

    private int computeDistance(int[] mapping, int[] reqMapping) {
        final int size = Math.min(mapping.length, reqMapping.length);
        int distance = 0;

        for (int i = 0; i < size; i++) {
            if (mapping[i] < reqMapping[i]) {
                distance++;
            }
        }

        return distance;
    }

    public List<int[]> getRequestedMappings() {
        return requestedMappings;
    }

    public void setRequestedMappings(List<int[]> requestedMappings) {
        this.requestedMappings = requestedMappings;
    }
}
