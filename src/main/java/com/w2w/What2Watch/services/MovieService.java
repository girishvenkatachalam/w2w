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

    public ResponseEntity getAllMovies() {
        Page<Movie> movies =  movieRepository.findAll( PageRequest.of(0, 2, Sort.Direction.DESC, "popularity"));
        HashMap<String, Page<Movie>> movieMap = new HashMap<String, Page<Movie>>();
        movieMap.put("Trending", movies);
        return ResponseEntity.status(HttpStatus.OK).body(movieMap);
    }
}
