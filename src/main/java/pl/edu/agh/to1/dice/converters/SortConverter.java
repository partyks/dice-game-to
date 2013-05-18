package pl.edu.agh.to1.dice.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.gameControllers.RankingController;
import pl.edu.agh.to1.dice.ranking.IUserSort;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Service
@FacesConverter(value="sortConverter")
public class SortConverter implements Converter {

    @Autowired
    RankingController rankingController;

    @Autowired
    ApplicationContext appCont;

    @PostConstruct
    public void dupa() {
        rankingController = (RankingController) appCont.getBean("rankingController");
        rankingController.getSorts();
        System.out.println("KURWAMAC");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + rankingController);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + rankingController);

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
