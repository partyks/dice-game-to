package pl.edu.agh.to1.dice.logic.figures;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Michal Partyka
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)  //TODO: change scope to session once app is web based
public class FigureManager implements IFigureManager {
    private List<IFigure> figures = new ArrayList<IFigure>();
    private List<IFigure> figuresCountedIntoBonus = new ArrayList<IFigure>();

    public void setConfiguration(AbstractConfigurationFactory factory) {
        figures.clear();
        figures.addAll(factory.createFigures());
        figuresCountedIntoBonus.addAll(factory.countForBonus());
    }


    @Override
    public List<IFigure> values() {
        return figures;
    }

    @Override
    public List<IFigure> countForBonus() {
        return figuresCountedIntoBonus;
    }

    @Override
    public IFigure getFigureBySignature(final String figureSignature) {
        return (IFigure) CollectionUtils.find(figures, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return ((IFigure) o).getSignature().equals(figureSignature);
            }
        });
    }
}
