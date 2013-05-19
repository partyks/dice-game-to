package pl.edu.agh.to1.dice.gameControllers.gameplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.figures.Bonus;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Component
public class ResultController {

    @Autowired
    private WebFlowController webFlowController;

    List<Bonus> getBonuses() {
        return null;
    }
}
