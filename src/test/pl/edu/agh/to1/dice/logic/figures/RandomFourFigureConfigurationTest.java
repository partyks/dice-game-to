package pl.edu.agh.to1.dice.logic.figures;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Michal Partyka
 */
public class RandomFourFigureConfigurationTest {
    @Test
    public void testCreateBonuses() throws Exception {
        AbstractConfigurationFactory configurationFactory = new RandomFigureConfiguration();
        assertThat(configurationFactory.createBonuses().size()).isGreaterThan(0);
    }
}
