package pl.edu.agh.to1.dice.playermodel;

import pl.edu.agh.to1.dice.StatisticsModel.GlobalStatistics;

import javax.persistence.*;

/**
 * @author Michal Partyka
 */
@Entity
public class UserModel {

    @GeneratedValue
    @Id
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private GlobalStatistics globalStatistics;

    public UserModel() {
    }

    public UserModel(String name, GlobalStatistics globalStatistics) {
        this.name = name;
        this.globalStatistics = globalStatistics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GlobalStatistics getGlobalStatistics() {
        return globalStatistics;
    }

    public void setGlobalStatistics(GlobalStatistics globalStatistics) {
        this.globalStatistics = globalStatistics;
    }
}
