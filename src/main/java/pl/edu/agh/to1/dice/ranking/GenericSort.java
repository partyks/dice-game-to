package pl.edu.agh.to1.dice.ranking;

import pl.edu.agh.to1.dice.playermodel.UserModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class implements some generic implementation of IUserSort, it needs comparator or/and
 * IExtractUserInfo for implementing IUserSort. It doesn't provide any defualt implementation
 * @author Michal Partyka
 */
public class GenericSort implements IUserSort {

    private final Comparator<UserModel> userComparator;
    private final IExtractUserInfo extractUserInfo;

    public GenericSort(Comparator<UserModel> userComparator) {
        this.userComparator = userComparator;
        extractUserInfo = new IExtractUserInfo() {
            @Override
            public UserInfo extractUserInfo(UserModel user) {
               return new UserInfo(user, "");
            }
        };
    }

    public GenericSort(Comparator<UserModel> userComparator, IExtractUserInfo extractUserInfo) {
        this.userComparator = userComparator;
        this.extractUserInfo = extractUserInfo;
    }

    @Override
    public List<UserInfo> sort(List<UserModel> users) {
        Collections.sort(users, userComparator);
        final List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (UserModel user : users) {
            userInfos.add(extractUserInfo.extractUserInfo(user));
        }
        return userInfos;
    }

    public Comparator<UserModel> getUserComparator() {
        return userComparator;
    }

    public IExtractUserInfo getExtractUserInfo() {
        return extractUserInfo;
    }

}
