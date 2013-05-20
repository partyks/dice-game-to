package pl.edu.agh.to1.dice.logic.dices;

import java.util.Random;

/**
 * Class which is a model of real dice. It provides methods for throwing and reading the score
 * @author Michal Partyka
 */
public class Dice implements Comparable<Dice> {
    private static Random random = new Random();
    protected Integer score = 0;

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

}
