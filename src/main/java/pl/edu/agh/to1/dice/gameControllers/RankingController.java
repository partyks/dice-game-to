package pl.edu.agh.to1.dice.gameControllers;

import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.ranking.GenericSort;
import pl.edu.agh.to1.dice.ranking.IUserSort;
import pl.edu.agh.to1.dice.ranking.SortUsersByWonGames;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.lang.ArrayStoreException;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Component
public class RankingController implements Serializable {

    private final List<IUserSort> sorts = Arrays.asList((IUserSort) new SortUsersByWonGames());

    private IUserSort selectedSort = sorts.get(0);

    public List<IUserSort> getSorts() {
        return sorts;
    }

    public IUserSort getSelectedSort() {
        return selectedSort;
    }

    public void setSelectedSort(IUserSort selectedSort) {
        this.selectedSort = selectedSort;
    }

}
