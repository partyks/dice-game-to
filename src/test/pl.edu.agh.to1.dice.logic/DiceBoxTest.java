package pl.edu.agh.to1.dice.logic;

import org.junit.Test;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Michal Partyka
 */
public class DiceBoxTest {
    @Test
    public void testSetFreeze() throws Exception {
        //given
        DiceBox diceBox = new DiceBox(5);

        //when
        diceBox.roll();
        int amount = diceBox.size();
        diceBox.setFreeze(2);

        //then
        assertThat(amount).isEqualTo(diceBox.size() + 1);
    }

    @Test
    public void testRollingClear() throws Exception {
        //given
        DiceBox diceBox = new DiceBox(5);

        //when
        diceBox.roll();
        diceBox.roll();
        diceBox.setFreeze(1);
        diceBox.setFreeze(2);

        int amount = diceBox.size();

        diceBox.roll();

        //then
        assertThat(amount).isEqualTo(3);
        assertThat(diceBox.size()).isEqualTo(5);
    }

    @Test
    public void testFreezingWithListAndClear() throws Exception {
        //given
        DiceBox diceBox = new DiceBox(5);

        diceBox.roll();
        diceBox.freeze(Arrays.asList(1,2));
        int amount = diceBox.size();
        diceBox.roll();

        assertThat(amount).isEqualTo(3);
        assertThat(diceBox.size()).isEqualTo(5);
    }

}
