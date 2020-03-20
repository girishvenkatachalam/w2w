package com.w2w.What2Watch.services;

import com.w2w.What2Watch.models.Keyword;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public  Page<Movie> getTrendingMovies() {
        Page<Movie> movies =  movieRepository.findAll( PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
        return movies;
    }

    public Movie getMovieByGivenId(String movieId) {
        Movie movie = new Movie();
        if("".equals(movieId.trim()) && movieId != null && !movieId.matches("[0-9]+")) {
            movie = movieRepository.findById(Integer.parseInt(movieId));
            return movie;
        }
        return null;
    }

    public HashMap<String, List<Movie>> getMoviesByPreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            List<Movie> movies = movieRepository.findByGenre(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
            if (movies.size() > 0) {
                movieMap.put(preference, movies);
            }
        }
        return movieMap;
    }

    public HashMap<String, List<Movie>> getMoviesByProductionCompanyPreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            List<Movie> movies = movieRepository.findByProductionCompany(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
            if (movies.size() > 0) {
                movieMap.put(preference, movies);
            }
        }
        return movieMap;
    }

    public HashMap<String, List<Movie>> getMoviesByLanguagePreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            List<Movie> movies =movieRepository.findBySpokenLanguage(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
            if (movies.size() > 0) {
                movieMap.put(preference, movies);
            }
        }
        return movieMap;
    }

    public List<Movie> getMovieByTitle(String movieTitle){
        return movieRepository.findByTitleIgnoreCase(movieTitle);
    }

    public List<Movie> getMovieSimilarToTitle(String movieTitle){
        return movieRepository.findByTitleLikeIgnoreCase(movieTitle);
    }

    public List<Movie> getMoviesByKeyword(String keyword){
        return movieRepository.findByKeyword(keyword);
    }
}
