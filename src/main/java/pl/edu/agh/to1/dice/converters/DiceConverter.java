package pl.edu.agh.to1.dice.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.gameControllers.gameplay.DiceBoxController;
import pl.edu.agh.to1.dice.logic.dices.Dice;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;
import java.util.Map;

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

        final Map<String,Dice> dices = diceBoxController.getDices();
        return dices.get(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }

        Dice dice = (Dice) value;
        final Map<String, Dice> dices = diceBoxController.getDices();
        String ret = "";
        for (Map.Entry<String, Dice> e : dices.entrySet()) {
            if (e.getValue().equals(dice)) {
                return e.getKey();
            }
        }

        return "";
    }

}
