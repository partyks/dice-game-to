package pl.edu.agh.to1.dice.logic.players.ai.freezing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.to1.dice.logic.figures.IFigure;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;

import javax.annotation.Resource;

/**
 * Author: Piotr Turek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationConfig.xml"})
public class EqualsSpecMethodTest {

    @Resource(name = "THREE_EQUALSSpecMethod")
    EqualsSpecMethod threeEqualsMethod;

    @Resource(name = "FOUR_EQUALSSpecMethod")
    EqualsSpecMethod fourEqualsMethod;

    @Resource(name = "figureManager")
    IFigureManager figureManager;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetValueCountsAndScore() throws Exception {

    }

    @Test
    public void testGetIndexesToFreeze3Eq() throws Exception {
        //given
        final IFigure threeEqualsFig = figureManager.getFigureByName("THREE_EQUALS");
        final
    }
}
