package br.com.andreguedes.filmes_mvp.network.api;

import java.util.List;

import br.com.andreguedes.filmes_mvp.pojo.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andreguedes on 25/06/17.
 */

public interface API {

    String END_POINT = "https://sky-exercise.herokuapp.com/";

    @GET("/api/movies")
    Call<List<Movie>> getMovies();

}