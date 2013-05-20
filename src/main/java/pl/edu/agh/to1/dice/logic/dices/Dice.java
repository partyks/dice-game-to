package pl.edu.agh.to1.dice.logic.dices;

import java.util.Random;

/**
 * Class which is a model of real dice. It provides methods for throwing and reading the score
 * @author Michal Partyka
 */
public class Dice implements Comparable<Dice> {
    private static Random random = new Random();
    private static int DID = 0;

    protected Integer score = 0;
    private Integer id = DID++;

    /**
     * random next dice score
     */
    public void roll() {
        //set score for random number from [1..6]
        score = random.nextInt(6) + 1;
    }

    public int getScore() {
        if (score == 0) {
            throw new IllegalStateException("Trying to read score of the dice, which haven't been thrown");
        }
        return score;
    }

    @Override
    public int compareTo(Dice o) {
        return score.compareTo(o.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dice dice = (Dice) o;

        if (!id.equals(dice.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
