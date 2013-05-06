package pl.edu.agh.to1.dice.logic.players;

import pl.edu.agh.to1.dice.App;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractPlayer implements Player {
    protected Score score = (Score) App.getBeanFactory().getBean("score");
    private String name;

    protected AbstractPlayer() {
    }

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "user " + name;
    }

    public Integer getScore(IFigure figure) {
        return score.getScore(figure);
    }

    public String getCurrentStock(DiceBox diceBox) {
        return score.currentStock(diceBox);
    }

    public Result getResult() {
        return score.getResult();
    }
}
