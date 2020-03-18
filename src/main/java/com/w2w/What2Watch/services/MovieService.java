package com.w2w.What2Watch.Service;

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

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public  Page<Movie> getTrendingMovies() {
        Page<Movie> movies =  movieRepository.findAll( PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"));
        return movies;
    }
}
