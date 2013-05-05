package pl.edu.agh.to1.dice.repository;

import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.repositoryInterfaces.IGenericDAO;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

/**
 * @author Michal Partyka
 */
public interface IGlobalStatisticDAO extends IGenericDAO<GlobalStatistics> {

    GlobalStatistics getGlobalStatisticsByUser(User user);
}
