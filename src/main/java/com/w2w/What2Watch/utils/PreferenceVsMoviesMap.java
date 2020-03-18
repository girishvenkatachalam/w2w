package com.w2w.What2Watch.utils;

import com.w2w.What2Watch.models.Movie;

import java.util.List;

public class PreferenceVsMoviesMap
{
    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    String preferenceName;
    List<Movie> movieList;

    public PreferenceVsMoviesMap(String preferenceName, List<Movie> movieList) {
        this.preferenceName = preferenceName;
        this.movieList = movieList;
    }
}
