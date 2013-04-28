package pl.edu.agh.to1.dice.logic.players.ai;

import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;
import pl.edu.agh.to1.dice.logic.*;
import pl.edu.agh.to1.dice.logic.figures.Figure;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.logic.players.ai.figurechoosing.IFigureChoosingStrategy;
import pl.edu.agh.to1.dice.logic.players.ai.freezing.IFreezingStrategy;

import java.util.logging.Logger;

/**
 * Author: Piotr Turek
 */
public class ModularBot implements Player {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private final String name;
    private final Score score = new Score();

    private IFreezingStrategy freezingStrategy;
    private IFigureChoosingStrategy figureChoosingStrategy;


    public ModularBot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "user " + name;
    }

    public void sparePoints(DiceBox diceBox) throws ReadingUserInputException {
        score.add(figureChoosingStrategy.chooseFigure(score, diceBox), diceBox);
    }

    public void manageDices(DiceBox diceBox) throws ReadingUserInputException {
        diceBox.freeze(freezingStrategy.getToFreeze(score, diceBox));
    }

    public Integer getScore(Figure figure) {
        return score.getScore(figure);
    }


    public String getCurrentStock(DiceBox diceBox) {
        return score.currentStock(diceBox);
    }

    public Result getResult() {
        return score.getResult();
    }

    public IFreezingStrategy getFreezingStrategy() {
        return freezingStrategy;
    }

    public void setFreezingStrategy(IFreezingStrategy freezingStrategy) {
        this.freezingStrategy = freezingStrategy;
    }

    public IFigureChoosingStrategy getFigureChoosingStrategy() {
        return figureChoosingStrategy;
    }

    public void setFigureChoosingStrategy(IFigureChoosingStrategy figureChoosingStrategy) {
        this.figureChoosingStrategy = figureChoosingStrategy;
    }
}
