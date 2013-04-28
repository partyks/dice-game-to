package pl.edu.agh.to1.dice.logic.figures;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Piotr Turek
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)  //TODO: change scope to session once app is web based
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
}
