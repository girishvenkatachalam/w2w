package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.services.MovieService;
import com.w2w.What2Watch.utils.PreferenceVsMoviesMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie-details")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ResponseEntity getMovieDetails(ModelMap modelMap)
    {
        Page<Movie> movies = movieService.getTrendingMovies();
        List<PreferenceVsMoviesMap> preferenceVsMoviesMap = new ArrayList<>();
        preferenceVsMoviesMap.add(new PreferenceVsMoviesMap("Trending", movies.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(preferenceVsMoviesMap);
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getUserByGivenEmail(@PathVariable("movieId") @NotEmpty String movieId) throws UserNotFoundException {
        return movieService.getMovieByGivenId(movieId);
    }
}

