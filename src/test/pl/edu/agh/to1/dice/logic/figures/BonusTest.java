package pl.edu.agh.to1.dice.logic.figures;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;


/**
 * @author Michal Partyka
 */
@RunWith(MockitoJUnitRunner.class)
public class BonusTest {

    private FigureDecorator figureDecorator = new FigureDecorator(Figure.ONES, 3);
    private List<IFigure> figures;
    private Bonus bonus;

    @Before
    public void setUp() throws Exception {
        figures = new ArrayList<>();
        figures.add(Figure.ONES);
        figures.add(figureDecorator);
        bonus = new Bonus(figures, 30, 70, "bonus");
    }

    @Test
    public void testGetCountedList() throws Exception {
        assertThat(bonus.getCountedList().size()).isEqualTo(2);
    }

    @Test
    public void testGetRequire() throws Exception {
        assertThat(bonus.getRequire()).isEqualTo(30);
    }

    @Test
    public void testGetBonus() throws Exception {
        assertThat(bonus.getBonus()).isEqualTo(70);
    }

    @Test
    public void testCountBonus() throws Exception {
        Map<IFigure, Integer> map = new HashMap<>();
        map.put(Figure.ONES, 15);
        map.put(figureDecorator, 15);
        bonus.countBonus(map);
        assertThat(bonus.getPoints()).isEqualTo(bonus.getBonus());
        map.put(Figure.ONES, 10);
        map.put(figureDecorator, 19);
        bonus.countBonus(map);
        assertThat(bonus.getPoints()).isEqualTo(0);
        map.clear();
    }

    @Test
    public void testGetPoints() throws Exception {
        assertThat(bonus.getPoints()).isEqualTo(0);
    }
}
