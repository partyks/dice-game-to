package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.figures.Bonus;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.Result;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
@Scope("session")
public class ResultController {

    @Autowired
    private IFigureManager  figureManager;

    public List<Bonus> getBonuses(final Player player) {
        Result result = player.getScore().getResult();
        return result.getBonuses();
    }

    public Integer result(final Player player) {
        return player.getResult().result();
    }
}
