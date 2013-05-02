package pl.edu.agh.to1.dice.repository;

import org.springframework.stereotype.Repository;
import pl.edu.agh.to1.dice.playermodel.UserModel;

/**
 * @author Michal Partyka
 */
@Repository
public class UserDAO extends GenericDAO<UserModel> implements IUserDAO {

    public UserDAO() {
        super(UserModel.class);
    }
}
