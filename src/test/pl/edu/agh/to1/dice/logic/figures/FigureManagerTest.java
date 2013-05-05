package pl.edu.agh.to1.dice.logic.figures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Michal Partyka
 */
public class FigureManagerTest {
    @Test
    public void testGetBonuses() throws Exception {
        AbstractConfigurationFactory abstractConfigurationFactory = mock(AbstractConfigurationFactory.class);
        Collection<Bonus> bonuses = Arrays.asList(new Bonus(Arrays.asList((IFigure) Figure.ONES),10,20,"asdf"));
        given(abstractConfigurationFactory.createBonuses()).willReturn(bonuses);
        FigureManager figureManager = new FigureManager();
        figureManager.setConfiguration(abstractConfigurationFactory);
        assertThat(figureManager.getBonuses()).isEqualTo(new ArrayList<Object>(bonuses));
    }
}
