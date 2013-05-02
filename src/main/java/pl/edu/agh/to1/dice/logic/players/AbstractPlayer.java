package pl.edu.agh.to1.dice.logic.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.to1.dice.App;
import pl.edu.agh.to1.dice.logic.dices.DiceBox;
import pl.edu.agh.to1.dice.logic.figures.IFigure;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractPlayer implements Player {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPlayer.class);
    private static final BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationConfig.xml");
    protected Score score = (Score) App.getBeanFactory().getBean("score");
    private String name;

    protected AbstractPlayer() {
    }

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "user " + name;
    }

    public Integer getScore(IFigure figure) {
        return score.getScore(figure);
    }

    public String getCurrentStock(DiceBox diceBox) {
        return score.currentStock(diceBox);
    }

    public Result getResult() {
        return score.getResult();
    }
}
