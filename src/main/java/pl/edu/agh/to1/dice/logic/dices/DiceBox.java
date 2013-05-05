package pl.edu.agh.to1.dice.logic.dices;

import java.util.*;

/**
 * Class which provides implementation for dice box behaviour. It returns some score maps of dices and other helpful
 * methods...
 * @author Michal Partyka
 */
public class DiceBox implements Iterable<Dice> {
    protected final List<Dice> dices = new ArrayList<Dice>(5);
    protected final List<Dice> frozenDices = new ArrayList<Dice>(5);

    public DiceBox(int amount) {
        for (int i = 0; i < amount; i++) {
            dices.add(new Dice());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Dices:\n");
        for (Dice dice : dices) {
            stringBuilder.append(dices.indexOf(dice)).append(". ").append(dice.getScore()).append("\n");
        }
        if (frozenDices.size() > 0) {
            stringBuilder.append("Frozen dices:");
            for (Dice frozenDice : frozenDices) {
                stringBuilder.append(frozenDices.indexOf(frozenDice)).append(". ").append(frozenDice.getScore())
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * roll every dice, one by one.
     */
    public void roll() {
        for (Dice dice : dices) {
            dice.roll();
        }
        prepare();
    }

    /**
     * freeze dices of provided index
     * @param indexForFreezing list of indexes of dices you want to freeze by invoking this method
     */
    public void freeze(Set<Integer> indexForFreezing) {
        final List<Dice> newFrozen = new ArrayList<Dice>();
        for (int freeze : indexForFreezing) {
            newFrozen.add(dices.get(freeze));
        }
        for (Dice dice : newFrozen) {
            dices.remove(dice);
        }
        frozenDices.addAll(newFrozen);
    }

    /**
     * freeze one dice
     * @param i index of that dice
     */
    public void setFreeze(int i) {
        Dice frozen = dices.remove(i);
        frozenDices.add(frozen);
    }

    /**
     * make every dice ready to roll (not frozen)
     */
    public void prepare() {
        for (Dice frozenDice : frozenDices) {
            dices.add(frozenDice);
        }
        frozenDices.clear();
    }

    /**
     * count the sum of dices score
     * @return Integer with sum of dices score
     */
    public Integer sum() {
        int sum = 0;
        for (Dice dice : dices) {
            sum += dice.getScore();
        }
        for (Dice frozenDice : frozenDices) {
            sum += frozenDice.getScore();
        }
        return sum;
    }

    /**
     * @return quantity with which DiceBox was created
     */
    public int quantity() {
        return dices.size() + frozenDices.size();
    }

    /**
     * Return map of dices scores from 0 to 6
     * Every integer in array is related to the next score.
     * 0 - 0 score
     * 1 - 1 score
     * For example {0, 1, 0, 0, 2, 3. 1} means that we have:
     * one one-score, zero two-score, zero three-score, two four-score, three five-score and one six-score
     */
    public int[] getMapCount() {
        int[] count = new int[7];
        Collections.sort(dices);
        for (Dice dice : dices) {
            count[dice.getScore()]++;
        }
        return count;
    }

    /**
     * Counts dices with provided score
     * @param lookFor score of the Dice we are counting
     * @return amount of dices which score is lookFor
     */
    public Integer count(Integer lookFor) {
        Integer amount = 0;
        for (Dice dice : dices) {
            if (dice.getScore() == lookFor) {
                amount++;
            }
        }
        return amount;
    }

    /**
     * Sum score of dices with provided score
     * @param lookFor score of the dices which are taking to the sum count
     * @return the sum
     */
    public Integer sum(Integer lookFor) {
        Integer result = 0;
        for (Dice dice : dices) {
            if (dice.getScore() == lookFor) {
                result += lookFor;
            }
        }
        return result;
    }


    /**
     * @return amout of not-frozen dices
     */
    public Integer size() {
        return dices.size();
    }

    @Override
    public Iterator<Dice> iterator() {
        return dices.iterator();
    }
}
