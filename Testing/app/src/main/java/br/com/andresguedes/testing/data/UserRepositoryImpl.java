package br.com.andresguedes.testing.data;

import java.io.IOException;
import java.util.List;

import br.com.andresguedes.testing.data.remote.GithubUserRestService;
import br.com.andresguedes.testing.data.remote.model.User;
import rx.Observable;

/**
 * Created by andreguedes on 13/09/17.
 */

public class UserRepositoryImpl implements UserRepository {

    private GithubUserRestService service;

    public UserRepositoryImpl(GithubUserRestService service) {
        this.service = service;
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        return Observable.defer(() -> service.searchGithubUsers(searchTerm)
                .concatMap(usersList -> Observable.from(usersList.getItems())
                        .concatMap(user -> service.getUser(user.getLogin())).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }

}