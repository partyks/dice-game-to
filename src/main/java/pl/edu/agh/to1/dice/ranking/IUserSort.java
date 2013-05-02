package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

import java.util.List;

/**
 * Interface which provide method which sorts users and wrap them into UserInfo for extracting the information about
 * sorted users.
 * @author Michal Partyka
 */
public interface IUserSort {
    List<UserInfo> sort(List<User> users);
}
