package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

/**
 * Wrapper for the User class. Provides also the user description displayed with user in current context
 * @author Michal Partyka
 */
public class UserInfo {
    private final User user;
    private final String description;

    public UserInfo(User user, String description) {
        this.user = user;
        this.description = description;
    }

    @Override
    public String toString() {
        return user.getName() + " " + description;
    }
}
