package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface IUserSort {
    List<UserInfo> sort(List<User> users);
}
