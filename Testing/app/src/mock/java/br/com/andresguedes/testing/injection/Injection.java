package br.com.andresguedes.testing.injection;

import br.com.andresguedes.testing.data.UserRepository;
import br.com.andresguedes.testing.data.UserRepositoryImpl;
import br.com.andresguedes.testing.data.remote.GithubUserRestService;
import br.com.andresguedes.testing.data.remote.MockGithubUserRestServiceImpl;

/**
 * Created by andreguedes on 13/09/17.
 */

public class Injection {

    private static GithubUserRestService githubUserRestService;

    public static UserRepository provideUserRepo() {
        return new UserRepositoryImpl(provideGithubUserRestService());
    }

    private static GithubUserRestService provideGithubUserRestService() {
        if (githubUserRestService == null)
            githubUserRestService = new MockGithubUserRestServiceImpl();
        return githubUserRestService;
    }

}