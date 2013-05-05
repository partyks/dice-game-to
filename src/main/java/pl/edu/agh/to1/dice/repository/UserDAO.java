package pl.edu.agh.to1.dice.repository;

import org.springframework.stereotype.Repository;
import pl.edu.agh.to1.dice.playermodel.UserModel;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Michal Partyka
 */
@Repository
public class UserDAO extends GenericDAO<UserModel> implements IUserDAO {

    public UserDAO() {
        super(UserModel.class);
    }

    // I don't admit that I've written this... URGHH :D (sorry, lack of time)
    public void add(UserModel userModel) throws UserAlreadyPersistedInDatabaseException {
        UserModel nullModel = null;
        try {
            nullModel = getUserByUsername(userModel.getName());
        } catch (NoResultException e) {
            // it's okey ;-)
        }
        if (nullModel != null) {
            throw new UserAlreadyPersistedInDatabaseException("Given username " + userModel.getName() + "  " +
                    "is already persisted in database");
        }
        super.add(userModel);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        CriteriaQuery<UserModel> criteriaQuery =
                getEntityManager().getCriteriaBuilder().createQuery(UserModel.class);
        Root<UserModel> from = criteriaQuery.from(UserModel.class);
        criteriaQuery.where(from.get("name").in(username));
        criteriaQuery.select(from);
        try {
            return getEntityManager().createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("No username with name: " + username);
        }
    }
}
