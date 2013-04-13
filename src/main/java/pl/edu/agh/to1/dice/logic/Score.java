package pl.edu.agh.to1.dice.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michal Partyka
 */
public class Score {
    private final Map<Figure, Integer> points = new HashMap<Figure, Integer>(16);
    final static List<Figure> countForBonus = Figure.countForBonus();

    public int add(Figure figure, DiceBox diceBox) {
        if (points.containsKey(figure)) {
            throw new IllegalStateException("Trying assign points for the already filled figure");
        }
        final Integer score = figure.getScore(diceBox);
        points.put(figure, score);
        return score;
    }

    public Result getResult() {
        int sum=0, bonusPoints=0, bonus=0;
        for (Map.Entry<Figure, Integer> figureIntegerEntry : points.entrySet()) {
            sum += figureIntegerEntry.getValue();
            if (countForBonus.contains(figureIntegerEntry.getKey())) {
                bonusPoints += figureIntegerEntry.getValue();
            }
        }
        if (bonusPoints > 63) {
            bonus = 35;
        }

        return new Result(sum, bonus);
    }

    public String currentStock(DiceBox diceBox) {
        String defualt = "                   |  ";
        final StringBuilder stringBuilder = new StringBuilder();
        for (Figure figure : Figure.values()) {
            stringBuilder.append(figure).append(defualt.substring(figure.toString().length()));
            if(!points.containsKey(figure)) {
                stringBuilder.append(figure.getScore(diceBox)).append("\n");
            } else {
                stringBuilder.append("-").append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public int getScore(Figure figure) {
        Integer ret = points.get(figure);
        return ret == null ? 0 : ret;
    }
}
