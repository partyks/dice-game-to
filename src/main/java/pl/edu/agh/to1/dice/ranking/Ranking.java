package pl.edu.agh.to1.dice.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.repository.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Ranking {
    private List<UserInfo> list = new ArrayList<UserInfo>();

    @Autowired
    private UserDAO userDAO;


    public void sort(IUserSort comparator) {
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
        System.out.println("Ranking sorted by ");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }

}
