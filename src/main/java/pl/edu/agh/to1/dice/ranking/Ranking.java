package pl.edu.agh.to1.dice.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.repository.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides implementation for displaying ranking. It uses IUserSort for sorting.
 * @author Michal Partyka
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Ranking {
    private List<UserInfo> list = new ArrayList<UserInfo>();
    private IUserSort comparator;

    @Autowired
    private UserDAO userDAO;


    public void sort(IUserSort comparator) {
        this.comparator = comparator;
        list = comparator.sort(userDAO.getList());
    }

    /**
     * Displays whole ranking
     */
    public void displayRanking() {
        displayRanking(list.size());
    }

    /**
     * Displays first players in the ranking
     * @param n number of players to display
     */
    public void displayRanking(int n) {
        System.out.println(getRanking(n));
    }

    public String getRanking() {
        return getRanking(list.size());
    }

    private String getRanking(int n) {
        if (comparator == null) {
            throw new IllegalStateException("Displaying ranking performed before sorting");
        }
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ranking sorted by ").append(comparator.toString());
        for (int i = 0; i < n; i++) {
            stringBuilder.append(i).append(". ").append(list.get(i));
        }
        return stringBuilder.toString();
    }

}
