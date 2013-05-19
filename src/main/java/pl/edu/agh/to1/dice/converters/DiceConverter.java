package pl.edu.agh.to1.dice.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.gameControllers.gameplay.DiceBoxController;
import pl.edu.agh.to1.dice.logic.dices.Dice;
import pl.edu.agh.to1.dice.ranking.IUserSort;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Component
public class DiceConverter implements Converter {

    @Autowired
    private DiceBoxController diceBoxController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value.equals("")) {
            return null;
        }

        final List<Dice> dices = diceBoxController.getDices();
        return dices.get(0);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "0";
        }

        Dice dice = (Dice) value;

        return String.valueOf(dice.getScore());
    }

}
