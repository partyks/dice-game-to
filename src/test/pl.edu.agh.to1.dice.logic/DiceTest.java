package pl.edu.agh.to1.dice.logic;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Michal Partyka
 */
public class DiceTest {
    @org.junit.Test
    public void testGetScore() throws Exception {
        //given
        Dice dice = new Dice();

        //when
        dice.roll();

        //then
        for (int i=0; i<128; i++) {
            dice.roll();
            assertThat(dice.getScore()).isGreaterThan(0);
            assertThat(dice.getScore()).isLessThan(7);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testWithNoThrow() throws Exception {
        //given
        Dice dice = new Dice();

        //when
        dice.getScore();
    }
}
