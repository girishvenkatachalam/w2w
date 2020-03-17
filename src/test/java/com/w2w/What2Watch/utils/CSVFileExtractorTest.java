package com.w2w.What2Watch.utils;

import com.w2w.What2Watch.models.Movie;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;

public class CSVFileExtractorTest {

    @Test
    public void testCSVParser() throws IOException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",1, movies.size());
        assertEquals("",19995, movies.get(0).id);
    }

    @Test
    public void testGenre() throws IOException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",2, movies.get(0).genre.size());

        assertEquals("",28, movies.get(0).genre.get(0).id);
        assertEquals("","Action", movies.get(0).genre.get(0).name);

        assertEquals("",12, movies.get(0).genre.get(1).id);
        assertEquals("","Adventure", movies.get(0).genre.get(1).name);
    }

    @Test
    public void testGenreProductionCompany() throws IOException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("",2, movies.get(0).productionCompany.size());

        assertEquals("",289, movies.get(0).productionCompany.get(0).id);
        assertEquals("","Ingenious Film Partners", movies.get(0).productionCompany.get(0).name);

        assertEquals("",306, movies.get(0).productionCompany.get(1).id);
        assertEquals("","Twentieth Century Fox Film Corporation",
                movies.get(0).productionCompany.get(1).name);
    }

    @Test
    public void testLanguage() throws IOException {
        List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
        assertEquals("","en", movies.get(0).language);
    }

    @Test
    public void testPopularity() throws IOException {
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
}