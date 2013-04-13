package pl.edu.agh.to1.dice.logic;

import java.util.List;

/**
 * @author Michal Partyka
 */
public class Table {
    private final List<User> users;

    public Table(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Current users: ");
        for (User user : users) {
            stringBuilder.append(user.toString() + " ");
        }
        return stringBuilder.toString();
    }
}
