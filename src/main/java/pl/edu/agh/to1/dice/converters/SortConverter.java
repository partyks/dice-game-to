package pl.edu.agh.to1.dice.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.gameControllers.RankingController;
import pl.edu.agh.to1.dice.ranking.IUserSort;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Author: Piotr Turek
 */
@Service
@ViewScoped
@FacesConverter(value="sortConverter")
public class SortConverter implements Converter {

    @Autowired
    RankingController rankingController;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        }

        for (IUserSort us : rankingController.getSorts()) {
            if (us.toString().equals(value)) {
                return us;
            }
        }

        return rankingController.getSorts().get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}
