package com.w2w.What2Watch.utils;

import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.Keyword;
import com.w2w.What2Watch.models.Movie;

import com.w2w.What2Watch.models.ProductionCompany;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.springframework.test.util.AssertionErrors.*;

public class CSVFileExtractorTest {

    @Test
    public void testCSVParser() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",1, movies.size());
        assertEquals("",19995, movies.get(0).id);
        assertEquals("", new GregorianCalendar(2009, 11, 10).getTime(),
                movies.get(0).releaseDate);
    }

    @Test
    public void testCSVParserEmptyReleaseDate() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie_empty_release_date.csv");
        assertEquals("",1, movies.size());
        assertEquals("",19995, movies.get(0).id);
        assertEquals("", null, movies.get(0).releaseDate);
    }

    @Test
    public void testGenre() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",2, movies.get(0).genre.size());

        assertEquals("",28, movies.get(0).genre.get(0).id);
        assertEquals("","Action", movies.get(0).genre.get(0).name);

        assertEquals("",12, movies.get(0).genre.get(1).id);
        assertEquals("","Adventure", movies.get(0).genre.get(1).name);
    }

    @Test
    public void testGenreProductionCompany() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",2, movies.get(0).productionCompany.size());

        assertEquals("",289, movies.get(0).productionCompany.get(0).id);
        assertEquals("","Ingenious Film Partners", movies.get(0).productionCompany.get(0).name);

        assertEquals("",306, movies.get(0).productionCompany.get(1).id);
        assertEquals("","Twentieth Century Fox Film Corporation",
                movies.get(0).productionCompany.get(1).name);
    }

    @Test
    public void testLanguage() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("","en", movies.get(0).language);
    }

    @Test
    public void testPopularity() throws IOException, ParseException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",150.437577, movies.get(0).popularity);
    }

    @Test
    public void testExtractIfNullFilePathProvided(){
        try {
            String filePath = null;
            List<Movie> lines = CSVFileExtractor.extract(filePath);
            fail("it should not reach here");
        } catch (Exception ex){
            assertEquals("", "File path can not be null.", ex.getMessage());
        }
    }

    @Test
    public void testExtractIfEmptyFilePathProvided(){
        try {
            String filePath = "";
            List<Movie> lines = CSVFileExtractor.extract(filePath);
            fail("File path can not be empty.");
        } catch (Exception ex){
            assertEquals("", "File path can not be empty.", ex.getMessage());
        }
    }

    @Test
    public void testGetAllGenresFromMovie() {
        Movie movie = new Movie();
        movie.genre.add(new Genre(1, "SCI-FI"));
        movie.genre.add(new Genre(2, "Action"));
        List<Genre> genres = CSVFileExtractor.getAllGenres(movie);
        assertEquals("Should not fail", 2, genres.size());
    }

    @Test
    public void testGetAllGenresFromMovieWithDuplicates() {
        Movie movie = new Movie();
        movie.genre.add(new Genre(1, "SCI-FI"));
        movie.genre.add(new Genre(2, "Action"));
        movie.genre.add(new Genre(2, "Action"));
        List<Genre> genres = CSVFileExtractor.getAllGenres(movie);
        assertEquals("Should not fail", 2, genres.size());
    }


    @Test
    public void testGetAllGenresFromMovies() {
        Movie movie = new Movie();
        movie.genre.add(new Genre(1, "SCI-FI"));
        movie.genre.add(new Genre(2, "Action"));

        Movie newMovie = new Movie();
        newMovie.genre.add(new Genre(1, "SCI-FI"));
        newMovie.genre.add(new Genre(2, "Thriller"));

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(newMovie);

        List<Genre> genres = CSVFileExtractor.getAllGenres(movies);
        assertEquals("Should not fail", 3, genres.size());
    }

    @Test
    public void testGetAllProductionCompaniesFromMovies() {
        Movie movie = new Movie();
        movie.productionCompany.add(new ProductionCompany(1, "Marvel"));
        movie.productionCompany.add(new ProductionCompany(2, "DC"));

        Movie newMovie = new Movie();
        newMovie.productionCompany.add(new ProductionCompany(1, "Marvel"));
        newMovie.productionCompany.add(new ProductionCompany(3, "Universal Studio"));

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(newMovie);

        List<ProductionCompany> productionCompanies = CSVFileExtractor.getAllProductionCompanies(movies);
        assertEquals("Should not fail", 3, productionCompanies.size());
    }

    @Test
    public void testGetAllKeywordsFromMovies() {
        Movie movie = new Movie();
        movie.keyword.add(new Keyword(1, "lang"));
        movie.keyword.add(new Keyword(2, "go get"));

        Movie newMovie = new Movie();
        newMovie.keyword.add(new Keyword(1, "lang"));
        newMovie.keyword.add(new Keyword(3, "social"));

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(newMovie);

        List<Keyword> keywords = CSVFileExtractor.getAllKeywords(movies);
        assertEquals("Should not fail", 3, keywords.size());
    }
}