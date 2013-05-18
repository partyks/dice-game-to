package pl.edu.agh.to1.dice.logic.figures;

import java.util.List;

/**
 * Author: Piotr Turek
 */
//@Component
//@Scope(BeanDefinition.SCOPE_SINGLETON)  //TODO: change scope to session once app is web based
public interface IFigureManager {
    /**
     * Returns IFigures used in the current configuration
     * @return List of figures
     */
    List<IFigure> values();

    /**
     * TODO: SORRY, wtf:D?
     * Returns IFigures that count for bonus
     * @return List of figures
     */
    IFigure getFigureBySignature(String figureSignature);

    List<Bonus> getBonuses();

    IFigure getFigureByName(String figureSignature);

    void setConfiguration(AbstractConfigurationFactory factory);
}
