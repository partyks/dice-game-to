package pl.edu.agh.to1.dice.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.gameControllers.RankingController;
import pl.edu.agh.to1.dice.ranking.IUserSort;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Service
public class SortConverter implements Converter {

    @Autowired
    private RankingController rankingController;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        }

        final List<IUserSort> sorts = rankingController.getSorts();

        for (IUserSort us : sorts) {
            if (us.toString().equals(value)) {
                return us;
            }
        }

        return sorts.get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}
