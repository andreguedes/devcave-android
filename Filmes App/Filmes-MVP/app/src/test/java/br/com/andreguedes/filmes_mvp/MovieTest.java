package br.com.andreguedes.filmes_mvp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import br.com.andreguedes.filmes_mvp.pojo.Movie;

import static org.junit.Assert.assertTrue;

/**
 * Created by andreguedes on 12/07/17.
 */

public class MovieTest {

    @Test
    public void testMovieBasicValuesDifferentEmptyCanShow() {
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setOverview("");
        movie.setCoverUrl("");
        movie.setDuration("");
        movie.setReleaseYear("");
        movie.setId("");

        boolean result = movie.isBasicValuesDifferentOfEmpty();

        assertTrue(result);
    }

    @Test
    public void testMovieWithBackdropsUrlMoreThenTwoCanShow() {
        List<String> backdropsUrl = new ArrayList<>();
        backdropsUrl.add("http://url1.com");
        backdropsUrl.add("http://url2.com");
        backdropsUrl.add("http://url3.com");

        Movie movie = new Movie();
        movie.setBackdropsUrl(backdropsUrl);

        boolean result = movie.isBackdropsUrlMoreThanTwo();

        assertTrue(result);
    }

}