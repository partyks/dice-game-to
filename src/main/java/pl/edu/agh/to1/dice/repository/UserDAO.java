package pl.edu.agh.to1.dice.repository;

import org.springframework.stereotype.Repository;
import pl.edu.agh.to1.dice.logic.players.User;

/**
 * @author Michal Partyka
 */
@Repository
public class UserDAO extends GenericDAO<User> {

    public UserDAO(Class<User> type) {
        super(User.class);
    }
}
