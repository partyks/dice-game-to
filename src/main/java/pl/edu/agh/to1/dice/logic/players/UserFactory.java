package pl.edu.agh.to1.dice.logic.players;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.playermodel.UserModel;

/**
 * @author Michal Partyka
 */
@Component
public class UserFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static User newInstance(UserModel userModel) {
        return new User(userModel, (Score) applicationContext.getBean("score"),
                (IFigureManager) applicationContext.getBean("figureManager"));
    }
}
