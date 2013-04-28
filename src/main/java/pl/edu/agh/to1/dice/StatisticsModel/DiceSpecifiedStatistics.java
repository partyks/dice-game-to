package pl.edu.agh.to1.dice.StatisticsModel;

import javax.persistence.Entity;

/**
 * @author Michal Partyka
 */
@Entity
public class DiceSpecifiedStatistics extends SpecifiedStatistics {
    private Integer sumOfPoints;
    private Integer sumOfBonusPoints;
}
