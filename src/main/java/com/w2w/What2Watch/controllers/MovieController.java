package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie-details")
public class MovieController {

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String getMovieDetails(ModelMap modelMap)
    {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.id = 0;
        movie.homepage = "abc";
        movie.title = "Avatar";
        movies.add(movie);
        modelMap.addAttribute("name", "Trending");
        modelMap.addAttribute("list", movies);
        return "home";
    }
}
