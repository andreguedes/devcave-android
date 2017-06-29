package br.com.andreguedes.filmes_mvp.models;

import br.com.andreguedes.filmes_mvp.contracts.MVP;
import br.com.andreguedes.filmes_mvp.network.retrofit.Service;
import br.com.andreguedes.filmes_mvp.network.routers.MoviesRouter;

/**
 * Created by andreguedes on 26/06/17.
 */

public class MovieModel implements MVP.MovieModelImpl {

    private MVP.MoviePresenterImpl presenter;

    public MovieModel(MVP.MoviePresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadMovies(MVP.MoviePresenterImpl callback) {
        this.presenter.showProgressBar(true);
        new MoviesRouter(Service.getService(), callback).getMovies();
    }

}