package pl.edu.agh.to1.dice.logic.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to1.dice.logic.DiceBox;
import pl.edu.agh.to1.dice.logic.Result;
import pl.edu.agh.to1.dice.logic.Score;
import pl.edu.agh.to1.dice.logic.figures.IFigure;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractPlayer implements Player {
    @Transient
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPlayer.class);

    @Column(unique = true)
    private String name;

    @Transient
    protected final Score score = new Score();

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
