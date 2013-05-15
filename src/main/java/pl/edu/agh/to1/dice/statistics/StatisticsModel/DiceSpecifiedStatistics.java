package pl.edu.agh.to1.dice.statistics.StatisticsModel;

import javax.persistence.Entity;

/**
 * Statistics which are specific for DiceGame
 * @author Michal Partyka
 */
//@Entity
public class DiceSpecifiedStatistics extends SpecifiedStatistics {
    private Integer sumOfPoints;
    private Integer sumOfBonusPoints;

    public DiceSpecifiedStatistics() {
    }

    public DiceSpecifiedStatistics(Integer sumOfPoints, Integer sumOfBonusPoints) {
        this.sumOfPoints = sumOfPoints;
        this.sumOfBonusPoints = sumOfBonusPoints;
    }

    public DiceSpecifiedStatistics(GameInfo gameInfo, Integer amountOfPlayedGames, Integer amountOfWinGames,
                                   Integer amountOfEscapes, Integer sumOfPoints, Integer sumOfBonusPoints) {
        super(gameInfo, amountOfPlayedGames, amountOfWinGames, amountOfEscapes);
        this.sumOfPoints = sumOfPoints;
        this.sumOfBonusPoints = sumOfBonusPoints;
    }

    public Integer getSumOfPoints() {
        return sumOfPoints;
    }

    public void setSumOfPoints(Integer sumOfPoints) {
        this.sumOfPoints = sumOfPoints;
    }

    public Integer getSumOfBonusPoints() {
        return sumOfBonusPoints;
    }

    public void setSumOfBonusPoints(Integer sumOfBonusPoints) {
        this.sumOfBonusPoints = sumOfBonusPoints;
    }
}
