package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.What2WatchApplication;
import com.w2w.What2Watch.models.*;
import com.w2w.What2Watch.repositories.*;
import com.w2w.What2Watch.utils.CSVFileExtractor;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ImportController {

    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ProductionCompanyRepository productionCompanyRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    //@GetMapping("/update-movie-image")
    public String updateMovieImage() throws Exception {

        List<Movie> movies = movieRepository.findAll();

        System.out.println("Updating movie image :");

        for (Movie movie : movies) {
            System.out.println("Movie id :" + movie.id + " " + movie.image );

            if (movie.image == null) {
                String posterPath = sendGet("https://api.themoviedb.org/3/movie/" + movie.id + "?api_key=24ff3fcde2d6b81a01a42f9cd75d9b08");

                if (posterPath != null) {
                    System.out.println("Poster path" + posterPath);

                    String posterUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
                    System.out.println("Poster url" + posterUrl);
                    movie.image = posterUrl;

                    movieRepository.save(movie);
                    System.out.println("Update movie id :" + movie.title);
                }
                //Thread.sleep(500);
            }
        }
        return "In Progress";
    }

    @GetMapping("/update-release-date")
    public String updateReleaseDate() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");

        for(Movie movie: movies) {
            Movie dbMovie = movieRepository.findById(movie.id);
            if (movie.releaseDate != null) {
                dbMovie.releaseDate = movie.releaseDate;
                movieRepository.save(dbMovie);
            }
        }

        return "Updated releaseDate";
    }

    //@GetMapping("/update-spoken-languages")
    public String updateSpokenLanguages() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");
        Set<SpokenLanguage> spokenLanguages = new HashSet<>();

        for(Movie movie: movies) {
            Movie dbMovie = movieRepository.findById(movie.id);
            if (dbMovie.spokenLanguage == null && movie.spokenLanguage != null) {
                dbMovie.spokenLanguage = movie.spokenLanguage;
                movieRepository.save(dbMovie);
                spokenLanguages.addAll(movie.spokenLanguage);
            }
        }

        languageRepository.insert(spokenLanguages);

        return "Added " + spokenLanguages.size() + " languages";
    }

    //@GetMapping("/update-keywords")
    public String updateKeywords() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");
        Set<Keyword> keywords = new HashSet<>();

        for(Movie movie: movies) {
            Movie dbMovie = movieRepository.findById(movie.id);
            if (dbMovie.keyword == null && movie.keyword != null) {
                dbMovie.keyword = movie.keyword;
                movieRepository.save(dbMovie);
                keywords.addAll(movie.keyword);
            }
        }

        keywordRepository.insert(keywords);

        return "Added " + keywords.size() + " keywords";
    }

    //@GetMapping("/add-all-keywords")
    public String addAllKeywords() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");
        Set<Keyword> keywords = new HashSet<>();

        for(Movie movie: movies) {
            if (movie.keyword != null) {
                keywords.addAll(movie.keyword);
            }
        }

        keywordRepository.insert(keywords);

        return "Added " + keywords.size() + " keywords";
    }

    //@GetMapping("/import")
    public String importFile() {
        try {
            List<Movie> movies = CSVFileExtractor.extract("src/main/resources/movie-dataset/tmdb_5000_movies.csv");
            int genreCount = 0;
            int productionCompaniesCount = 0;
            if (movies != null && movies.size() > 0) {
                List<Genre> genres = CSVFileExtractor.getAllGenres(movies);
                List<ProductionCompany> productionCompanies = CSVFileExtractor.getAllProductionCompanies(movies);
                genreCount = genres.size();
                productionCompaniesCount = productionCompanies.size();

                movieRepository.deleteAll();
                genreRepository.deleteAll();
                productionCompanyRepository.deleteAll();

                movieRepository.insert(movies);
                genreRepository.insert(genres);
                productionCompanyRepository.insert(productionCompanies);
            }

            return "Imported "
                    + movies.size() + " movies, "
                    + genreCount + " genres, "
                    + productionCompaniesCount
                    + " production companies";
        } catch (Exception ex) {
            return "Exception occured: " + ex.getMessage();
        }
    }

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println("Response status" + response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            String posterPath = null;

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                JSONObject movieJson = new JSONObject(result);
                posterPath = (movieJson != null)?
                        movieJson.getString("poster_path") : null;
            }

            return posterPath;
        }

    }
}
