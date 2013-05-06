package pl.edu.agh.to1.dice.logic.players.ai.figurechoosing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Score;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.*;

/**
 * Greedy figure choosing strategy, which will always (with a given probability) choose figure that seems
 * optimal in the current point of time.
 *
 * Author: Piotr Turek
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GreedyChoosingStrategy implements IFigureChoosingStrategy {
    @Autowired
    private IFigureManager figureManager;

    private double willTakeBest;

    public GreedyChoosingStrategy() {
    }

    /**
     *
     * @param willTakeBest probability of strategy accepting the best choice
     * @throws IllegalArgumentException if willTakeBest is not from range (0, 1)
     */
    public GreedyChoosingStrategy(double willTakeBest) {
        if (willTakeBest < 0. || willTakeBest > 1.) {
            throw new IllegalArgumentException("Bad coefficient!");
        }

        this.willTakeBest = willTakeBest;
    }

    @Override
    public IFigure chooseFigure(Score score, DiceBox diceBox) {
        List<Pair<IFigure, Integer>> choices = new LinkedList<Pair<IFigure, Integer>>();

        for (IFigure figure : figureManager.values()) {
            if (!score.isUsed(figure)) {
                final Integer willGet = figure.getScore(diceBox);
                choices.add(new Pair<IFigure, Integer>(figure, willGet));
            }
        }

        Collections.sort(choices, new Comparator<Pair<IFigure, Integer>>() {
            @Override
            public int compare(Pair<IFigure, Integer> lhs, Pair<IFigure, Integer> rhs) {
                return rhs.getRight() - lhs.getRight();
            }
        });

        IFigure ret = null;
        Random rand = new Random();

        for (Pair<IFigure, Integer> choice : choices) {
            ret = choice.getLeft();
            if (rand.nextInt(100) < willTakeBest * 100) break;
        }

        return ret;
    }

    /**
     * Returns probability of strategy accepting the best choice.
     * @return double from range (0, 1)
     */
    public double getWillTakeBest() {
        return willTakeBest;
    }

    /**
     * Sets probability of strategy accepting the best choice
     * @param willTakeBest probability of strategy accepting the best choice
     * @throws IllegalArgumentException if willTakeBest is not from range (0, 1)
     */
    public void setWillTakeBest(double willTakeBest) {
        if (willTakeBest < 0. || willTakeBest > 1.) {
            throw new IllegalArgumentException("Bad coefficient!");
        }

        this.willTakeBest = willTakeBest;
    }
}
