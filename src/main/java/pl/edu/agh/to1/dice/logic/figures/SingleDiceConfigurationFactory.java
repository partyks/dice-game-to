package pl.edu.agh.to1.dice.logic.figures;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class SingleDiceConfigurationFactory extends AbstractConfigurationFactory {

    @Override
    public List<IFigure> createFigures() {
        return figures;
    }

    @Override
    public Collection<? extends Bonus> createBonuses() {
        List<Bonus> bonuses = new ArrayList<Bonus>(1);
        bonuses.add(new Bonus(figuresCountedForBonus, 65, 35, "Bonus"));
        return bonuses;
    }
}
