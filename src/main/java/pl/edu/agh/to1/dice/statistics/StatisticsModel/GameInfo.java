package pl.edu.agh.to1.dice.statistics.StatisticsModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Information about some game
 * @author Michal Partyka
 */
//@Entity
public class GameInfo {

//    @Id
//    @GeneratedValue
    Integer Id;

    private String name;
    private String description;
    private Integer maximumAmountOfPlayers;

    public GameInfo() {
    }

    public GameInfo(String name, String description, Integer maximumAmountOfPlayers) {
        this.name = name;
        this.description = description;
        this.maximumAmountOfPlayers = maximumAmountOfPlayers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaximumAmountOfPlayers() {
        return maximumAmountOfPlayers;
    }
}
