package br.com.andreguedes.filmes_mvp.contracts;

import java.util.List;

import br.com.andreguedes.filmes_mvp.pojo.Movie;

/**
 * Created by andreguedes on 26/06/17.
 */

public interface MVP {

    interface MovieModelImpl {
        void loadMovies(MoviePresenterImpl callback);
    }

    interface MovieViewImpl extends APICallback<List<Movie>> {
        void showProgressBar(int visibility);
    }

    interface MoviePresenterImpl {
        void loadMovies();
        void showProgressBar(boolean status);
        void updateMovies(List<Movie> movies);
        void setView(MovieViewImpl view);
    }

}