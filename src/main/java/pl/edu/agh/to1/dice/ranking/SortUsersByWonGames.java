package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.playermodel.UserModel;

import java.io.Serializable;
import java.util.Comparator;

/**
 * IUserSort implementation for sorting by won games
 * @author Michal Partyka
 */
public class SortUsersByWonGames extends GenericSort implements IUserSort {

    public SortUsersByWonGames() {
        super(new Comparator<UserModel>() {
            @Override
            public int compare(UserModel o1, UserModel o2) {
                return o2.getGlobalStatistics().getAmountOfWinGames().
                        compareTo(o1.getGlobalStatistics().getAmountOfWinGames());
            }
        },
        new IExtractUserInfo() {
            @Override
            public UserInfo extractUserInfo(UserModel user) {
                return new UserInfo(user,
                        user.getGlobalStatistics().getAmountOfWinGames().toString());
            }
        });
    }

    @Override
    public String toString() {
        return "Most wins";
    }
}
