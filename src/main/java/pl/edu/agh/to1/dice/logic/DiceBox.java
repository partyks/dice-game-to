package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class DiceBox {
    private final List<Dice> dices = new ArrayList<Dice>(5);
    private final List<Dice> frozenDices = new ArrayList<Dice>(5);

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

    public void roll() {
        for (Dice dice : dices) {
            dice.roll();
        }
        clear();
    }

    public void freeze(List<Integer> indexForFreezing) {
        final List<Dice> newFrozen = new ArrayList<Dice>();
        for (int freeze : indexForFreezing) {
            newFrozen.add(dices.get(freeze));
        }
        for (Dice dice : newFrozen) {
            dices.remove(dice);
        }
        frozenDices.addAll(newFrozen);
    }

    public void setFreeze(int i) {
        Dice frozen = dices.remove(i);
        frozenDices.add(frozen);
    }

    public void clear() {
        for (Dice frozenDice : frozenDices) {
            dices.add(frozenDice);
        }
        frozenDices.clear();
    }

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

    public int quantity() {
        return dices.size() + frozenDices.size();
    }

    public List<Dice> getDices() {
        return dices;
    }

    /*Only non-freeze dices will be counted!*/
    public int[] getMapCount() {
        int[] count = new int[7];
        Collections.sort(dices);
        for (Dice dice : dices) {
            count[dice.getScore()]++;
        }
        return count;
    }

    public Integer count(Integer lookFor) {
        Integer amount = 0;
        for (Dice dice : dices) {
            if (dice.getScore() == lookFor) {
                amount++;
            }
        }
        return amount;
    }

    public Integer sum(Integer lookFor) {
        Integer result = 0;
        for (Dice dice : dices) {
            if (dice.getScore() == lookFor) {
                result += lookFor;
            }
        }
        return result;
    }


}
