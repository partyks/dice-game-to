package pl.edu.agh.to1.dice.statistics.StatisticsModel;

import javax.persistence.*;

/**
 * Specific statistic for the GameInfo game
 * @author Michal Partyka
 */
//@Entity
public class SpecifiedStatistics {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private GameInfo gameInfo;

    private Integer amountOfPlayedGames;
    private Integer amountOfWinGames;
    private Integer amountOfEscapes;

    public SpecifiedStatistics() {
    }

    public SpecifiedStatistics(GameInfo gameInfo, Integer amountOfPlayedGames, Integer amountOfWinGames, Integer amountOfEscapes) {
        this.gameInfo = gameInfo;
        this.amountOfPlayedGames = amountOfPlayedGames;
        this.amountOfWinGames = amountOfWinGames;
        this.amountOfEscapes = amountOfEscapes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
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
}
