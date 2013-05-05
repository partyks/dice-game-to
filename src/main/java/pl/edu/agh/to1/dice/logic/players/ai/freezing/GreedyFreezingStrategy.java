package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.players.Score;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.*;

/**
 * Greedy freezing strategy, which will always (with a given probability) choose what seems
 * optimal in the current point of time. It orders possible choices by a quality coefficient Q, which is equal to the
 * product of points received for the given figure and probability (in range (0,1)) of getting that configuration after
 * freezing certain dices.
 *
 * Note that this class is application context aware and it uses specialized 'method classes' to compute quality
 * coefficient for every figure. Every IFigure (excluding decorators) implementation must have its SpecMethod bean with
 * name equal to figureSignature + 'SpecMethod'.
 *
 * Author: Piotr Turek
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GreedyFreezingStrategy implements IFreezingStrategy {
    @Autowired
    private IFigureManager figureManager;

    @Autowired
    private ApplicationContext applicationContext;

    private double willTakeBest;

    public GreedyFreezingStrategy() {
        willTakeBest = 0.85;
    }

    /**
     *
     * @param willTakeBest probability of strategy accepting the best choice
     * @throws IllegalArgumentException if willTakeBest is not from range (0, 1)
     */
    public GreedyFreezingStrategy(double willTakeBest) {
        if (willTakeBest < 0. || willTakeBest > 1.) {
            throw new IllegalArgumentException("Bad coefficient!");
        }

        this.willTakeBest = willTakeBest;
    }

    @Override
    public List<Integer> getToFreeze(Score score, DiceBox diceBox) {
        List<Pair<List<Integer>, Double>> choices = new LinkedList<Pair<List<Integer>, Double>>();

        for (IFigure figure : figureManager.values()) {
            if (!score.isUsed(figure)) {
                final String specMethodSignature = figure.getSignature() + "SpecMethod";
                if (!applicationContext.containsBean(specMethodSignature)) {
                    throw new SpecMethodMissingException("Spec Method " + specMethodSignature + " is missing from the" +
                            " application context!");
                }
                final ISpecMethod specMethod = applicationContext.getBean(specMethodSignature, ISpecMethod.class);
                choices.add(specMethod.computeQuality(figure, diceBox));
            }
        }

        Collections.sort(choices, new Comparator<Pair<List<Integer>, Double>>() {
            @Override
            public int compare(Pair<List<Integer>, Double> lhs, Pair<List<Integer>, Double> rhs) {
                return rhs.getRight().compareTo(lhs.getRight());
            }
        });

        List<Integer> ret = null;
        Random rand = new Random();

        for (Pair<List<Integer>, Double> choice : choices) {
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
