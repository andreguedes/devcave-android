package br.com.andreguedes.filmes_mvp.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.andreguedes.filmes_mvp.R;
import br.com.andreguedes.filmes_mvp.contracts.MVP;
import br.com.andreguedes.filmes_mvp.database.model.Movie;
import br.com.andreguedes.filmes_mvp.presenters.MoviePresenter;
import br.com.andreguedes.filmes_mvp.views.adapters.MoviesAdapter;

public class MoviesActivity extends AppCompatActivity implements MVP.MovieViewImpl {

    private RecyclerView rvMovies;
    private MoviesAdapter adapter;

    private static MVP.MoviePresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    }

    @Override
    protected void onStart() {
        super.onStart();

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MoviesAdapter(this, new ArrayList<Movie>());
        rvMovies.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (presenter == null)
            presenter = new MoviePresenter();
        presenter.setView(this);
        presenter.loadMovies();
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        adapter.updateMovies(movies);
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void showProgressBar(int visibility) {
        findViewById(R.id.includeProgressBar).setVisibility(visibility);
    }

}