package pl.edu.agh.to1.dice.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to1.dice.logic.GameResult;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.repository.IGlobalStatisticDAO;
import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

import java.util.logging.Logger;

/**
 * @author Michal Partyka
 */
@Service
public class StatisticService implements IStatisticService {
    private static final Logger LOGGER = Logger.getLogger(StatisticService.class.getName());
    @Autowired
    private IGlobalStatisticDAO globalStatisticDAO;

    @Override
    public void updateStatistics(GameResult result) {
        for (Player player : result.getPlayers()) {
            if ( player instanceof User) {
                GlobalStatistics userStatistics = globalStatisticDAO.getGlobalStatisticsByUser((User) player);
                LOGGER.info("trying to get statistic of user:\n" +
                player.getName() + "\n with id: " + ((User) player).getUserModel().getId());
                userStatistics.played();
                if (result.getWinners().contains(player)) {
                    userStatistics.won();
                }
                globalStatisticDAO.update(userStatistics);
            }
        }
    }
}
