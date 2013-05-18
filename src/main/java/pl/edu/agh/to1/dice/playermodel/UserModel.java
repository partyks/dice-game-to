package pl.edu.agh.to1.dice.playermodel;

import pl.edu.agh.to1.dice.statistics.StatisticsModel.GlobalStatistics;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class UserModel implements Serializable {

    private static final Long serialVersionID = 214219412421L;

    @GeneratedValue
    @Id
    private Integer id=0;

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

    public UserModel(String name) {
        this.name = name;
        this.globalStatistics = new GlobalStatistics(0,0,0);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;

        UserModel userModel = (UserModel) o;

        if (!name.equals(userModel.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
