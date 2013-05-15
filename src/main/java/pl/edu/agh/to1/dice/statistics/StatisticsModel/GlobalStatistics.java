package pl.edu.agh.to1.dice.statistics.StatisticsModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Global statistics of all games, aggregates specific statistics for every game
 * @author Michal Partyka
 */
//@Entity
public class GlobalStatistics {
    @GeneratedValue
    @Id
    private Integer Id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SpecifiedStatistics> specifiedStatisticsList = new ArrayList<SpecifiedStatistics>();

    private Integer amountOfPlayedGames;
    private Integer amountOfWinGames;
    private Integer amountOfEscapes;

    public GlobalStatistics() {
    }

    public GlobalStatistics(Integer amountOfPlayedGames, Integer amountOfWinGames, Integer amountOfEscapes) {
        this.amountOfPlayedGames = amountOfPlayedGames;
        this.amountOfWinGames = amountOfWinGames;
        this.amountOfEscapes = amountOfEscapes;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getAmountOfPlayedGames() {
        return amountOfPlayedGames;
    }

    public void setAmountOfPlayedGames(Integer amountOfPlayedGames) {
        this.amountOfPlayedGames = amountOfPlayedGames;
    }

    public Integer getAmountOfWinGames() {
        return amountOfWinGames;
    }

    public void setAmountOfWinGames(Integer amountOfWinGames) {
        this.amountOfWinGames = amountOfWinGames;
    }

    public Integer getAmountOfEscapes() {
        return amountOfEscapes;
    }

    public void setAmountOfEscapes(Integer amountOfEscapes) {
        this.amountOfEscapes = amountOfEscapes;
    }

    public void played() {
        amountOfPlayedGames++;
    }

    public void won() {
        amountOfWinGames++;
    }

    @Override
    public String toString() {
        return "GlobalStatistics{" +
                "Id=" + Id +
                '}';
    }
}
