package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.logic.players.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Michal Partyka
 */
public class GenericSort implements IUserSort {
    private final Comparator<User> userComparator;
    private final IExtractUserInfo extractUserInfo;

    public GenericSort(Comparator<User> userComparator) {
        this.userComparator = userComparator;
        extractUserInfo = new IExtractUserInfo() {
            @Override
            public UserInfo extractUserInfo(User user) {
               return new UserInfo(user, "");
            }
        };
    }

    public GenericSort(Comparator<User> userComparator, IExtractUserInfo extractUserInfo) {
        this.userComparator = userComparator;
        this.extractUserInfo = extractUserInfo;
    }

    @Override
    public List<UserInfo> sort(List<User> users) {
        Collections.sort(users, userComparator);
        final List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (User user : users) {
            userInfos.add(extractUserInfo.extractUserInfo(user));
        }
        return userInfos;
    }
}
