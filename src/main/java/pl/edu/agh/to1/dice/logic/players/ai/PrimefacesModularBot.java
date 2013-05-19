package pl.edu.agh.to1.dice.logic.players.ai;

import org.primefaces.context.RequestContext;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.players.Score;

/**
 * Author: Piotr Turek
 */
public class PrimefacesModularBot extends ModularBot {

    private RequestContext requestContext;

    public PrimefacesModularBot() {
        super();
    }

    public PrimefacesModularBot(String name) {
        super(name);
    }

    public PrimefacesModularBot(String name, Score score) {
        super(name, score);
    }

    @Override
    public void manageDices(DiceBox diceBox) throws ReadingUserInputException {
        super.manageDices(diceBox);
        final RequestContext rc = RequestContext.getCurrentInstance();
        rc.update(":mainForm:scoreTable :mainForm:rollsLeft :mainForm:freezingChoice :mainForm:rollButton");
    }

    @Override
    public void sparePoints(DiceBox diceBox) throws ReadingUserInputException {
        super.sparePoints(diceBox);
        final RequestContext rc = RequestContext.getCurrentInstance();
        rc.update(":mainForm");
    }
}
