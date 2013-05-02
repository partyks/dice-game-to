package pl.edu.agh.to1.dice.repository;

import org.springframework.stereotype.Repository;
import pl.edu.agh.to1.dice.logic.players.User;

/**
 * @author Michal Partyka
 */
@Repository
public class UserDAO extends GenericDAO<User> implements IUserDAO {

    public UserDAO() {
        super(User.class);
    }
}
