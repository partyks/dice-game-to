package pl.edu.agh.to1.dice.gameControllers;

import pl.edu.agh.to1.dice.ranking.GenericSort;
import pl.edu.agh.to1.dice.ranking.SortUsersByWonGames;

import java.io.Serializable;
import java.lang.ArrayStoreException;
import java.util.Arrays;

/**
 * Author: Piotr Turek
 */
public class RankingController implements Serializable {

    private final List<GenericSort> sorts = Arrays.asList(new SortUsersByWonGames());

    private GenericSort selectedSort = null;

    public List<GenericSort> getSorts() {
        return sorts;
    }

    public GenericSort getSelectedSort() {
        return selectedSort;
    }

    public void setSelectedSort(GenericSort selectedSort) {
        this.selectedSort = selectedSort;
    }

}
