package pl.edu.agh.to1.dice.logic.figures;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michal Partyka
 */
public abstract class AbstractConfigurationFactory {
    protected List<IFigure> figures = new ArrayList<IFigure>(10);
    protected List<IFigure> figuresCountedForBonus = new ArrayList<IFigure>();

    public AbstractConfigurationFactory() {
        figures.addAll(Arrays.asList(Figure.values()));
        figuresCountedForBonus.addAll(figures);
        CollectionUtils.filter(figuresCountedForBonus, new Predicate() {
            @Override
            public boolean evaluate(Object arg) {
                return Figure.countForBonus().contains(arg);
            }
        });
    }

    abstract List<IFigure> createFigures();
    abstract List<IFigure> countForBonus();
}
