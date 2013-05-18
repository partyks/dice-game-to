package pl.edu.agh.to1.dice.gameControllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.players.User;

/**
 * @author Michal Partyka
 */
@Controller
@Scope("session")
public class WebFlowController implements FlowController {
    @Override
    public User getCurrentUser() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean mustChooseFigure() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean canRoll() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean hasFinished() {
        //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
