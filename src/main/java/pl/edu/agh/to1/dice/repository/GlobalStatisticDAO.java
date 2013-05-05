package pl.edu.agh.to1.dice.repository;

import org.springframework.stereotype.Repository;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

import javax.persistence.TypedQuery;

/**
 * @author Michal Partyka
 */
@Repository
public class GlobalStatisticDAO extends GenericDAO<GlobalStatistics> implements IGlobalStatisticDAO {
    public GlobalStatisticDAO() {
        super(GlobalStatistics.class);
    }

    @Override
    public GlobalStatistics getGlobalStatisticsByUser(User user) {
        final TypedQuery<GlobalStatistics> tq = getEntityManager().createQuery("SELECT um.globalStatistics FROM UserModel um WHERE um.id = :id", GlobalStatistics.class).setParameter("id", user.getUserModel().getId());
        return tq.getSingleResult();
    }
}
