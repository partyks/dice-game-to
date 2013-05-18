package pl.edu.agh.to1.dice.gameControllers.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.figures.AbstractConfigurationFactory;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;

/**
 * @author Michal Partyka
 */
@Controller
public class ConfigurationController implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);
    private String gameMode = "tripleDiceConfigurationFactory";

    private ApplicationContext applicationContext;

    @Autowired
    private IFigureManager figureManager;

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        LOGGER.debug("Game mode set to: " + gameMode);
    }

    public void updateConfiguration() {
        LOGGER.debug("Updating configuration to: " + gameMode);
        figureManager.setConfiguration((AbstractConfigurationFactory) applicationContext.getBean(gameMode));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
