package pl.edu.agh.to1.dice.repository;

import pl.edu.agh.to1.dice.playermodel.UserModel;
import pl.edu.agh.to1.dice.repositoryInterfaces.IGenericDAO;

/**
 * @author Michal Partyka
 */
public interface IUserDAO extends IGenericDAO<UserModel> {
    UserModel getUserByUsername(String username);
}
