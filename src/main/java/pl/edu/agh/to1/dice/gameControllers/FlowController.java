package pl.edu.agh.to1.dice.gameControllers;

import pl.edu.agh.to1.dice.logic.players.User;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface FlowController {
    User getCurrentUser();
    Boolean mustChooseFigure();
    Boolean canRoll();
    Boolean hasFinished();
    void play(List<User> userList);
}
