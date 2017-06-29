package br.com.andreguedes.filmes_mvp.network.routers;

import java.util.List;

import br.com.andreguedes.filmes_mvp.contracts.MVP;
import br.com.andreguedes.filmes_mvp.database.model.Movie;
import br.com.andreguedes.filmes_mvp.network.api.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andreguedes on 25/06/17.
 */

public class MoviesRouter {

    private API api;
    private MVP.MoviePresenterImpl presenter;

    public MoviesRouter(API api, MVP.MoviePresenterImpl presenter) {
        this.api = api;
        this.presenter = presenter;
    }

    public void getMovies() {
        if (api != null) {
            api.getMovies().enqueue(new Callback<List<Movie>>() {
                @Override
                public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                    if (response.isSuccessful()) {
                        List<Movie> moviesList = response.body();
                        if (moviesList != null && presenter != null)
                            presenter.updateMovies(moviesList);
                    }
                }

                @Override
                public void onFailure(Call<List<Movie>> call, Throwable t) {
                    if (presenter != null)
                        presenter.updateMovies(null);
                }
            });
        }
    }

}