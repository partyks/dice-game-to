package pl.edu.agh.to1.dice.logic.players;

import pl.edu.agh.to1.dice.logic.figures.Bonus;

import java.util.List;

/**
 * Class store the Dice Game result of one user, his normal points and bonus
 * @author Michal Partyka
 */
public class Result {
    private final int sum;
    private final List<Bonus> bonuses;

    public Result(int sum, List<Bonus> bonus) {
        this.sum = sum;
        this.bonuses = bonus;
    }

    public Integer result() {
        int result = sum;
        for (Bonus bonuse : bonuses) {
            result += bonuse.getPoints();
        }
        return result;
    }

    public int getSum() {
        return sum;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Bonus bonuse : bonuses) {
            stringBuilder.append(bonuse.toString()).append('\n');
        }
        stringBuilder.append("Sum: ").append(result()).append('\n');
        return stringBuilder.toString();
    }
}
