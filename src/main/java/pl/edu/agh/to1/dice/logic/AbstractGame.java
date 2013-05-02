package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.StatisticsModel.GameInfo;
import pl.edu.agh.to1.dice.TUI.ReadingUserInputException;

import javax.persistence.*;

/**
 * @author Michal Partyka
 */
@Entity
abstract class AbstractGame implements IGameInfo {
    @Id
    @GeneratedValue
    private Integer Id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private GameInfo gameInfo;


    protected AbstractGame() {
    }

    protected AbstractGame(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public abstract void play() throws ReadingUserInputException;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}
