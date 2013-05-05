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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FigureDecorator)) return false;

        FigureDecorator that = (FigureDecorator) o;

        if (!figure.equals(that.figure)) return false;
        if (!multiplication.equals(that.multiplication)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = figure.hashCode();
        result = 31 * result + multiplication.hashCode();
        return result;
    }
}
