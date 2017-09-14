package br.com.andresguedes.testing.data;

import java.util.List;

import br.com.andresguedes.testing.data.remote.model.User;
import rx.Observable;

/**
 * Created by andreguedes on 13/09/17.
 */

public interface UserRepository {

    Observable<List<User>> searchUsers(String searchTerm);

}