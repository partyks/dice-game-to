package pl.edu.agh.to1.dice.gameControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.User;

import java.util.List;

/**
 * @author Michal Partyka
 */
@Controller
@Scope("session")
public class WebFlowController implements FlowController {
    @Autowired
    private IFigureManager figureManager;

    private List<User> users;

    @Override
    public User getCurrentUser() {

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

    @Override
    public void play(List<User> userList) {
        //TODO whatever - it sucks...
        this.users = userList;
    }


}
