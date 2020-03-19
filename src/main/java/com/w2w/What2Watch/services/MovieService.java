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
    // Get user preferences from the db -> call to user service
    // Iterate over each preference and return individual list back to the UI

    public HashMap<String, List<Movie>> getMoviesByPreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            movieMap.put(preference, movieRepository.findByGenre(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity")));
        }
        return movieMap;
    }

    public HashMap<String, List<Movie>> getMoviesByProductionCompanyPreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            movieMap.put(preference, movieRepository.findByProductionCompany(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity")));
        }
        return movieMap;
    }

    public HashMap<String, List<Movie>> getMoviesByLanguagePreference(List<String> preferences)
    {
        HashMap<String, List<Movie>> movieMap = new HashMap<>();
        for(String preference: preferences)
        {
            movieMap.put(preference, movieRepository.findByLanguage(preference, PageRequest.of(0, 4, Sort.Direction.DESC, "popularity")));
        }
        return movieMap;
    }
}
