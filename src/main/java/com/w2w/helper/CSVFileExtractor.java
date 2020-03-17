package com.w2w.helper;

import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileExtractor {
    public static List<Movie> extract(String path) throws IOException {
        if(path == null){
            throw  new RuntimeException("File path can not be null.");
        }

        if("".equals(path)){
            throw  new RuntimeException("File path can not be empty.");
        }

        return getData(path);
    }

    private static List<Movie> getData(String filePath) throws IOException {
        Reader in = new FileReader(filePath);
        CSVParser parser = CSVFormat.EXCEL.withHeader().parse(in);
        List<CSVRecord> csvRecords = parser.getRecords();
        List<Movie> movies = new ArrayList<>();

        int index = 0;
        int length = csvRecords.size();
        for (;index < length; index ++ ){
            movies.add(getMovie(csvRecords.get(index)))   ;
        }

        return movies;
    }

    private static Movie getMovie(CSVRecord record) {
        Movie movie = new Movie();
        movie.id = Integer.parseInt(record.get("id"));
        movie.title = record.get("title");
        movie.homepage = record.get("homepage");
        movie.originalTitle = record.get("original_title");
        movie.overview = record.get("overview");

        movie.genre = getGenres(record.get("genres"));
        return movie;
    }

    private static List<Genre> getGenres(String genreString) {
        List<Genre> genres = new ArrayList<>();
        JSONArray arr = new JSONArray(genreString);
        for (int i=0; i<arr.length(); i++) {
            JSONObject genreJson = arr.getJSONObject(i);
            int id = genreJson.getInt("id");
            String name = genreJson.getString("name");
            genres.add(new Genre(id, name));
        }
        return genres;
    }
}