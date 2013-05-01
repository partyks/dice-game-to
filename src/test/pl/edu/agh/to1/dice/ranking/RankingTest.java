package pl.edu.agh.to1.dice.ranking;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.to1.dice.StatisticsModel.GlobalStatistics;
import pl.edu.agh.to1.dice.logic.players.User;
import pl.edu.agh.to1.dice.repository.UserDAO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;


/**
 * That's not real unit test its rather some kind of integration test but..... sorry for that;-)
 * @author Michal Partyka
 */
@RunWith(MockitoJUnitRunner.class)
public class RankingTest {
    @Mock private UserDAO userDAO;
    @InjectMocks private Ranking ranking;

    @Before
    public void init() {
        final List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
            User user = new User(Integer.valueOf(i).toString());
            user.setGlobalStatistics(new GlobalStatistics(3,2,5));
            userList.add(user);
        }
        userList.get(2).setGlobalStatistics(new GlobalStatistics(1,1,3));
        userList.get(3).setGlobalStatistics(new GlobalStatistics(8,8,2));
        when(userDAO.getList()).thenReturn(userList);
        ranking.sort( new SortUsersByWonGames() );
    }

    @Test
    public void displayTest() {
        ranking.sort(new SortUsersByWonGames());
        assertThat(ranking.getRanking()).isEqualTo("Ranking sorted by amount of winning games " +
                "comparator0. 2 11. 0 22. 1 23. 4 24. 3 8");
    }
}
