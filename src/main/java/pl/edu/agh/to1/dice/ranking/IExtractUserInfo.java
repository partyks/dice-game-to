package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

/**
 * @author Michal Partyka
 */
public interface IExtractUserInfo {
    UserInfo extractUserInfo(User user);
}
