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
     * Returns IFigures that count for bonus
     * @return List of figures
     */
    List<IFigure> countForBonus();

    IFigure getFigureBySignature(String figureSignature);
}
