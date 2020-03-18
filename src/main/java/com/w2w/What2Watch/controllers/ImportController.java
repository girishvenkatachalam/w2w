package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.repositories.GenreRepository;
import com.w2w.What2Watch.repositories.MovieRepository;
import com.w2w.What2Watch.utils.CSVFileExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImportController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/import")
    public String importFile() {
        try {
            List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");
            int genreCount = 0;
            if(movies != null && movies.size() > 0) {
                List<Genre> genres = CSVFileExtractor.getAllGenres(movies);
                genreCount = genres.size();
                movieRepository.deleteAll();
                genreRepository.deleteAll();

                movieRepository.insert(movies);
                genreRepository.insert(genres);
            }

            return "Imported " + movies.size() + " movies, " + genreCount + " genres";
        }
        catch (Exception ex){
            return "Exception occured: " + ex.getMessage();
        }
    }
}
