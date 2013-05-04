package pl.edu.agh.to1.dice.logic.figures;

import pl.edu.agh.to1.dice.logic.dices.DiceBox;

/**
 * @author Michal Partyka
 */
public class FigureDecorator implements IFigure {
    private final IFigure figure;
    private final Integer multiplication;

    public FigureDecorator(IFigure figure, Integer multiplication) {
        this.figure = figure;
        this.multiplication = multiplication;
    }


    @Override
    public Integer getScore(DiceBox diceBox) {
        return multiplication*figure.getScore(diceBox);
    }

    @Override
    public String getSignature() {
        return figure.getSignature();
    }

    @Override
    public String toString() {
        return figure.toString()+"x"+multiplication.toString();
    }
}
