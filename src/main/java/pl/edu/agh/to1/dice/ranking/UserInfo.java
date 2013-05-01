package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

/**
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
