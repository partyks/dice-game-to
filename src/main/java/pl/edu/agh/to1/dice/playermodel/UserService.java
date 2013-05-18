package pl.edu.agh.to1.dice.playermodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.repository.IUserDAO;
import pl.edu.agh.to1.dice.repository.UserAlreadyPersistedInDatabaseException;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Service
public class UserService {
    @Autowired
    private IUserDAO userDAO;

    public UserModel[] displayAvailableUsers() {
        System.out.println("Users obtained from db:");
        List<UserModel> users = getUserModels();
        UserModel[] usersArray = users.toArray(new UserModel[users.size()]);
        for (int i = 0; i < usersArray.length; i++) {
            System.out.println(i + ". " + usersArray[i].getName());
        }
        return usersArray;
    }

    public List<UserModel> getUserModels() {
        return userDAO.getList();
    }

    public UserModel getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public void persist(UserModel userModel) throws UserAlreadyPersistedInDatabaseException {
        userDAO.add(userModel);
    }
}
