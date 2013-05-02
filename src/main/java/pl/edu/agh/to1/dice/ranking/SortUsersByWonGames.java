package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

import java.util.Comparator;

/**
 * IUserSort implementation for sorting by won games
 * @author Michal Partyka
 */
public class SortUsersByWonGames extends GenericSort implements IUserSort {

    public SortUsersByWonGames() {
        super(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getGlobalStatistics().getAmountOfWinGames().
                        compareTo(o2.getGlobalStatistics().getAmountOfWinGames());
            }
        },
        new IExtractUserInfo() {
            @Override
            public UserInfo extractUserInfo(User user) {
                return new UserInfo(user,
                        user.getGlobalStatistics().getAmountOfWinGames().toString());
            }
        });
    }

    @Override
    public String toString() {
        return "amount of winning games comparator";
    }
}
