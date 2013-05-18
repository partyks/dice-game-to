package pl.edu.agh.to1.dice.logic.figures;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Michal Partyka
 */
@Component
public class RandomFigureConfiguration extends TripleDiceConfigurationFactory {
    private static final Random random = new Random();
    private List<IFigure> randomFigures;


    @Override
    public List<IFigure> createFigures() {
        if (randomFigures == null) {
            randomFigures = randFigures();
        }
        return randomFigures;
    }

    @Override
    public Collection<Bonus> createBonuses() {
        if (randomFigures == null) {
            randomFigures = randFigures();
        }
        return Arrays.asList(new Bonus(randomFigures, 50, 1000, "1000 for 50!"));
    }

    private List<IFigure> randFigures() {
        final List<IFigure> figures = super.createFigures();
        final List<IFigure> rolled = new ArrayList<>();
        for (IFigure figure : figures) {
            if (random.nextInt(60) < 2) {
                rolled.add(figure);
            }
        }
        if (rolled.size() < 2) {
            rolled.add(new FigureDecorator(Figure.ONES, 10));
        }
        return rolled;
    }
}
