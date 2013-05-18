package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.playermodel.UserModel;

import java.util.Comparator;

/**
 * Author: Piotr Turek
 */
public class SortUsersByPlayedGames extends GenericSort implements IUserSort {

    public SortUsersByPlayedGames() {
        super(new Comparator<UserModel>() {
                  @Override
                  public int compare(UserModel o1, UserModel o2) {
                      return o2.getGlobalStatistics().getAmountOfPlayedGames().
                              compareTo(o1.getGlobalStatistics().getAmountOfPlayedGames());
                  }
              },
                new IExtractUserInfo() {
                    @Override
                    public UserInfo extractUserInfo(UserModel user) {
                        return new UserInfo(user,
                                user.getGlobalStatistics().getAmountOfPlayedGames().toString());
                    }
                });
    }

    @Override
    public String toString() {
        return "Most played";
    }
}
