package pl.edu.agh.to1.dice.logic.players;

import org.junit.Test;
import pl.edu.agh.to1.dice.logic.figures.Bonus;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Michal Partyka
 */
public class ResultTest {
    @Test
    public void testResult() throws Exception {
        Bonus bonus = mock(Bonus.class);
        given(bonus.getPoints()).willReturn(50);
        Result result = new Result(50, Arrays.asList(bonus));
        assertThat(result.result()).isEqualTo(100);
    }
}
