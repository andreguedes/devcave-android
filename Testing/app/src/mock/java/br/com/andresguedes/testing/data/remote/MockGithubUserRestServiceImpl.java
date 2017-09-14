package br.com.andresguedes.testing.data.remote;

import java.util.ArrayList;
import java.util.List;

import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.data.remote.model.UsersList;
import rx.Observable;

/**
 * Created by andreguedes on 14/09/17.
 */

public class MockGithubUserRestServiceImpl implements GithubUserRestService {

    private final List<User> usersList = new ArrayList<>();
    private User dummyUser1, dummyUser2;
    private static Observable dummyGithubSearchResult = null;

    public MockGithubUserRestServiceImpl() {
        dummyUser1 = new User("andresguedes", "Andre Guedes",
                "https://avatars0.githubusercontent.com/u/4925429?v=4", "Android/iOS Dev");
        dummyUser2 = new User("robxinai", "Robson Moreira",
                "https://avatars0.githubusercontent.com/u/5639827?v=4", "Android/iOS Dev");
        usersList.add(dummyUser1);
        usersList.add(dummyUser2);
    }

    public static void setDummyGithubSearchResult(Observable result) {
        dummyGithubSearchResult = result;
    }

    @Override
    public Observable<UsersList> searchGithubUsers(String serachTerm) {
        if (dummyGithubSearchResult != null)
            return dummyGithubSearchResult;
        return Observable.just(new UsersList(usersList));
    }

    @Override
    public Observable<User> getUser(String username) {
        if (username.equals("andresguedes"))
            return Observable.just(dummyUser1);
        else if (username.equals("robxinai"))
            return Observable.just(dummyUser2);
        return Observable.just(null);
    }

}