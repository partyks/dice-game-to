package pl.edu.agh.to1.dice.gameControllers.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to1.dice.logic.figures.IFigureManager;
import pl.edu.agh.to1.dice.logic.players.Player;
import pl.edu.agh.to1.dice.logic.players.Score;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.logic.players.ai.BotManager;
import pl.edu.agh.to1.dice.playermodel.UserModel;
import pl.edu.agh.to1.dice.playermodel.UserService;
import pl.edu.agh.to1.dice.repository.UserAlreadyPersistedInDatabaseException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Controller
public class PlayerConfiguration {
    @Autowired
    private UserService userService;

    @Autowired
    private BotManager botManager;

    @Autowired
    private ApplicationContext applicationContext;

    private Integer amountOfUsers = 0;
    private Integer amountOfBots = 0;

    private Double botStrength = 0.85;

    private List<UserModel> selectedUsers = new ArrayList<>();

    private boolean validAmountsOfPlayers;
    private boolean validConfiguration;

    private List<UserModel> availableUsers;

    public List<Player> createConfiguredUsers() {
        List<Player> players = new ArrayList<>(amountOfUsers+amountOfBots);
        players.addAll(botManager.createBots(amountOfBots, botStrength));
        for (UserModel selectedUser : selectedUsers) {
            players.add(new User(selectedUser, (Score) applicationContext.getBean("score"),
                    (IFigureManager) applicationContext.getBean("figureManager")));
        }

        return players;
    }

    public void addAndSelectUser(UserModel user) {
        try {
            userService.persist(user);
            availableUsers.add(user);
        } catch (UserAlreadyPersistedInDatabaseException e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("Cannot add this user, already exist in database"));
        }
        selectedUsers.add(user);
    }

    public boolean isValidAmountsOfPlayers() {
        validAmountsOfPlayers = amountOfUsers + amountOfBots > 0;
        return validAmountsOfPlayers;
    }

    public boolean isValidConfiguration() {
        if (selectedUsers == null) {
            validConfiguration = isValidAmountsOfPlayers() && amountOfUsers == 0;
        } else {
            System.out.println("SelectedUsers.size(): " + selectedUsers.size());
            validConfiguration = isValidAmountsOfPlayers() && (amountOfUsers.equals(selectedUsers.size()));
        }
        return validConfiguration;
    }

    public List<UserModel> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<UserModel> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public Integer getAmountOfBots() {
        return amountOfBots;
    }

    public void setAmountOfBots(Integer amountOfBots) {
        this.amountOfBots = amountOfBots;
    }

    public Integer getAmountOfUsers() {
        return amountOfUsers;
    }

    public void setAmountOfUsers(Integer amountOfUsers) {
        this.amountOfUsers = amountOfUsers;
    }

    public Double getBotStrength() {
        return botStrength;
    }

    public void setBotStrength(Double botStrength) {
        this.botStrength = botStrength;
    }

    public List<UserModel> getAvailableUsers() {
        if(availableUsers == null) {
            availableUsers = userService.getUserModels();
        }
        return availableUsers;
    }
}
