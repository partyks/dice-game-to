package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Service("CHANCESpecMethod")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ChanceSpecMethod extends AbstractSpecMethod {

    @Override
    protected Pair<List<Integer>, Double> getValueCountsAndScore(IFigure figure, DiceBox diceBox, List<Integer> toFreeze) {
        return new Pair<List<Integer>, Double>(Collections.<Integer>emptyList(), (double) figure.getScore(diceBox));
    }

    @Override
    protected List<Integer> getIndexesToFreeze(IFigure figure, DiceBox diceBox) {
        List<Integer> toFreeze = new ArrayList<Integer>(5);
        for (int i = 0; i < 6; i++) {
            toFreeze.set(i, i);
        }
        return toFreeze;
    }
}
