package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.playermodel.UserModel;

/**
 * Interface which wrap the User into UserInfo class
 * @author Michal Partyka
 */
public interface IExtractUserInfo {
    UserInfo extractUserInfo(UserModel user);
}
