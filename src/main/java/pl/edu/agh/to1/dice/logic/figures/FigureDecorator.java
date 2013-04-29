package pl.edu.agh.to1.dice.logic.figures;

import pl.edu.agh.to1.dice.logic.DiceBox;

/**
 * @author: Michal Partyka
 */
public class FigureDecorator implements IFigure {
    private IFigure figure;


    @Override
    public Integer getScore(DiceBox diceBox) {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getSignature() {
        return figure.getSignature();
    }
}
