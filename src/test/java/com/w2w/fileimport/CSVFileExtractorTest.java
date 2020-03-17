package com.w2w.fileimport;

import com.w2w.helper.CSVFileExtractor;
import com.w2w.model.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;

public class CSVFileExtractorTest {

    @Test
    public void testCSVParser() {
        try {
            List<Movie> movies = CSVFileExtractor.extract("src/test/resources/tmdb_1_movie.csv");
            assertEquals("",1, movies.size());
            assertEquals("",19995, movies.get(0).id);
            assertEquals("","Avatar", movies.get(0).title);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
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