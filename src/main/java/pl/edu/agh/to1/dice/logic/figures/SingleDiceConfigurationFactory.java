package pl.edu.agh.to1.dice.logic.figures;


import java.util.List;

/**
 * @author Michal Partyka
 */
public class SingleDiceConfigurationFactory extends AbstractConfigurationFactory {

    @Override
    List<IFigure> createFigures() {
        return figures;
    }

    @Override
    List<IFigure> countForBonus() {
        return figuresCountedForBonus;
    }
}
