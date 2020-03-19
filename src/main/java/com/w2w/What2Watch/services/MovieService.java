package com.w2w.What2Watch.services;

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
        Movie movie = movieRepository.findById(Integer.parseInt(movieId));
        return movie;
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
            List<Movie> movies =movieRepository.findByLanguage(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
            if (movies.size() > 0) {
                movieMap.put(preference, movies);
            }
        }
        return movieMap;
    }
}
