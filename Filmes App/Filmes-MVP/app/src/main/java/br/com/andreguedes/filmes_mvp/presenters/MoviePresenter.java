package br.com.andreguedes.filmes_mvp.presenters;

import android.view.View;

import java.util.List;

import br.com.andreguedes.filmes_mvp.contracts.MVP;
import br.com.andreguedes.filmes_mvp.database.model.Movie;
import br.com.andreguedes.filmes_mvp.models.MovieModel;

/**
 * Created by andreguedes on 26/06/17.
 */

public class MoviePresenter implements MVP.MoviePresenterImpl {

    private MVP.MovieModelImpl movieModel;
    private MVP.MovieViewImpl movieView;

    public MoviePresenter() {
        movieModel = new MovieModel(this);
    }

    @Override
    public void loadMovies() {
        movieModel.loadMovies(this);
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibility = status ? View.VISIBLE : View.GONE;
        movieView.showProgressBar(visibility);
    }

    @Override
    public void updateMovies(List<Movie> movies) {
        if (movies != null)
            movieView.onSuccess(movies);
        else
            movieView.onFailed();

        showProgressBar(false);
    }

    @Override
    public void setView(MVP.MovieViewImpl view) {
        this.movieView = view;
    }

}