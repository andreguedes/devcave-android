package br.com.andreguedes.filmes_mvp.pojo;

import java.util.List;

/**
 * Created by andreguedes on 25/06/17.
 */

public class Movie {

    private String title;
    private String overview;
    private String duration;
    private String releaseYear;
    private String coverUrl;
    private List<String> backdropsUrl = null;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<String> getBackdropsUrl() {
        return backdropsUrl;
    }

    public void setBackdropsUrl(List<String> backdropsUrl) {
        this.backdropsUrl = backdropsUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // TESTS
    public boolean isBasicValuesDifferentOfEmpty() {
        return getTitle().isEmpty() && getOverview().isEmpty() && getDuration().isEmpty() && getReleaseYear().isEmpty() && getCoverUrl().isEmpty() && getId().isEmpty();
    }

    public boolean isBackdropsUrlMoreThanTwo() {
        return getBackdropsUrl().size() > 2;
    }

}