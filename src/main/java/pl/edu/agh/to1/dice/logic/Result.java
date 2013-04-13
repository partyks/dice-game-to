package pl.edu.agh.to1.dice.logic;

/**
 * @author Michal Partyka
 */
public class Result {
    private final int sum;
    private final int bonus;

    public Result(int sum, int bonus) {
        this.sum = sum;
        this.bonus = bonus;
    }

    public int result() {
        return sum+bonus;
    }

    public int getSum() {
        return sum;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return (sum+bonus) + " (with " + bonus + " bonus)";
    }
}
