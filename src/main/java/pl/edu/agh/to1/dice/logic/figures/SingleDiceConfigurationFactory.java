package pl.edu.agh.to1.dice.logic.figures;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
public class SingleDiceConfigurationFactory extends AbstractConfigurationFactory {

    @Override
    public List<IFigure> createFigures() {
        return figures;
    }

    @Override
    public Collection<Bonus> createBonuses() {
        List<Bonus> bonuses = new ArrayList<Bonus>(1);
        bonuses.add(new Bonus(figuresCountedForBonus, 65, 35, "Bonus"));
        return bonuses;
    }
}
