package pl.edu.agh.to1.dice.gameControllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.ranking.IUserSort;
import pl.edu.agh.to1.dice.ranking.SortUsersByAbandonedGames;
import pl.edu.agh.to1.dice.ranking.SortUsersByPlayedGames;
import pl.edu.agh.to1.dice.ranking.SortUsersByWonGames;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Piotr Turek
 */
@Component
@Scope("session")
public class RankingController implements Serializable {

    private final List<IUserSort> sorts = Arrays.asList((IUserSort) new SortUsersByWonGames(),
            (IUserSort) new SortUsersByAbandonedGames(),
            (IUserSort) new SortUsersByPlayedGames());

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
