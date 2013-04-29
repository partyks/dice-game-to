package pl.edu.agh.to1.dice.logic.figures;

import java.util.List;

/**
 * @author: Michal Partyka
 */
public class TripleDiceConfigurationFactory extends AbstractConfigurationFactory {
    @Override
    List<IFigure> createFigures() {
        for (IFigure figure : figures) {
            figures.add(new FigureDecorator(figure, 2));
            figures.add(new FigureDecorator(figure, 3));
        }
        return figures;
    }

    @Override
    List<IFigure> countForBonus() {
        for (IFigure figure : figuresCountedForBonus) {
            figuresCountedForBonus.add(new FigureDecorator(figure, 2));
            figuresCountedForBonus.add(new FigureDecorator(figure, 3));
        }
        return figuresCountedForBonus;
    }
}
