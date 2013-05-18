package pl.edu.agh.to1.dice.logic.figures;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)  //TODO: change scope to session once app is web based
public class FigureManager implements IFigureManager {
    private List<IFigure> figures = new ArrayList<IFigure>();
    private List<Bonus> bonuses = new ArrayList<Bonus>();

    @Override
    public void setConfiguration(AbstractConfigurationFactory factory) {
        figures.clear();
        figures.addAll(factory.createFigures());
        bonuses.clear();
        bonuses.addAll(factory.createBonuses());
    }


    @Override
    public List<IFigure> values() {
        return figures;
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

    @Override
    public List<Bonus> getBonuses() {
        return bonuses;
    }

    @Override
    public IFigure getFigureByName(final String figureSignature) {
        IFigure ret = (IFigure) CollectionUtils.find(figures, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return ((IFigure) o).toString().equals(figureSignature);
            }
        });
        if (ret == null) {
            throw new IllegalArgumentException("Unfortunetly there is no provided figure signature");
        }
        return ret;
    }
}
