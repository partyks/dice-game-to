package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.playermodel.UserModel;

import java.util.Comparator;

/**
 * Author: Piotr Turek
 */
public class SortUsersByAbandonedGames extends GenericSort implements IUserSort {

    public SortUsersByAbandonedGames() {
        super(new Comparator<UserModel>() {
                  @Override
                  public int compare(UserModel o1, UserModel o2) {
                      return o2.getGlobalStatistics().getAmountOfEscapes().
                              compareTo(o1.getGlobalStatistics().getAmountOfEscapes());
                  }
              },
                new IExtractUserInfo() {
                    @Override
                    public UserInfo extractUserInfo(UserModel user) {
                        return new UserInfo(user,
                                user.getGlobalStatistics().getAmountOfEscapes().toString());
                    }
                });
    }

    @Override
    public String toString() {
        return "Most abandoned";
    }
}
