package pl.edu.agh.to1.dice.logic.figures;

import java.util.*;

/**
 * @author Michal Partyka
 */
public class RandomFourFigureConfiguration extends TripleDiceConfigurationFactory {
    private static final Random random = new Random();


    @Override
    public List<IFigure> createFigures() {
        final List<IFigure> figures = super.createFigures();
        final List<IFigure> rolled = new ArrayList<>();
        for (IFigure figure : figures) {
            if (random.nextInt(60) < 2) {
                rolled.add(figure);
            }
        }
        return rolled;
    }

    @Override
    public Collection<? extends Bonus> createBonuses() {
        return Arrays.asList(new Bonus(createFigures(), 50, 1000, "1000 for 50!"));
    }
}
