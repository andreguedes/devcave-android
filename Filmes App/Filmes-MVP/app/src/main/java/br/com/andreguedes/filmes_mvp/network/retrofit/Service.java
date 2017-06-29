package br.com.andreguedes.filmes_mvp.network.retrofit;

import br.com.andreguedes.filmes_mvp.network.api.API;

/**
 * Created by andreguedes on 25/06/17.
 */

public class Service {

    public static API getService() {
        return Client.getClient().create(API.class);
    }

}