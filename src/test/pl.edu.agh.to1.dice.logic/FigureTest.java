package pl.edu.agh.to1.dice.logic;

import org.junit.Test;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.Figure;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


/**
 * @author Michal Partyka
 */
public class FigureTest {
    @Test
    public void testGetScore() throws Exception {
        DiceBox diceBox = mock(DiceBox.class);
        given(diceBox.getMapCount()).willReturn(new int[] {0, 0, 1, 1, 1, 1, 0});

        assertThat(Figure.SMALL_STRIT.getScore(diceBox)).isEqualTo(30);
    }
}
