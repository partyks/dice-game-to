package pl.edu.agh.to1.dice.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.repository.IUserDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides implementation for displaying ranking. It uses IUserSort for sorting.
 * @author Michal Partyka
 */
@Component
@Scope("session")
public class Ranking implements Serializable {
    private List<UserInfo> userInfos = new ArrayList<UserInfo>();
    private IUserSort comparator;

    @Autowired(required = true)
    private IUserDAO userDAO;


    public void sort(IUserSort comparator) {
        this.comparator = comparator;
        userInfos = comparator.sort(userDAO.getList());
    }

    /**
     * Displays whole ranking
     */
    public void displayRanking() {
        displayRanking(userInfos.size());
    }

    /**
     * Displays first players in the ranking
     * @param n number of players to display
     */
    public void displayRanking(int n) {
        System.out.println(getRanking(n));
    }

    public String getRanking() {
        return getRanking(userInfos.size());
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    private String getRanking(int n) {
        if (comparator == null) {
            throw new IllegalStateException("Displaying ranking performed before sorting");
        }
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ranking sorted by ").append(comparator.toString()).append("\n");
        for (int i = 0; i < n; i++) {
            stringBuilder.append(i+1).append(". ").append(userInfos.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

}
