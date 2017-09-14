package br.com.andresguedes.testing.data.remote;

import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.data.remote.model.UsersList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andreguedes on 13/09/17.
 */

public interface GithubUserRestService {

    @GET("/search/users?per_page=10")
    Observable<UsersList> searchGithubUsers(@Query("q") String serachTerm);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);

}