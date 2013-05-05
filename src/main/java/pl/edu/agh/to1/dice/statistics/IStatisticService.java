package pl.edu.agh.to1.dice.statistics;

import pl.edu.agh.to1.dice.logic.GameResult;

/**
 * @author Michal Partyka
 */
public interface IStatisticService {
    void updateStatistics(GameResult result);
}
