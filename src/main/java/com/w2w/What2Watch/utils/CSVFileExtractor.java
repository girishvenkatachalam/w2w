package com.w2w.What2Watch.utils;

import com.w2w.What2Watch.models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVFileExtractor {
    public static List<Movie> extract(String path) throws IOException, ParseException {
        if(path == null){
            throw  new RuntimeException("File path can not be null.");
        }

        if("".equals(path)){
            throw  new RuntimeException("File path can not be empty.");
        }

        return getData(path);
    }

    private static List<Movie> getData(String filePath) throws IOException, ParseException {
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

    private static Movie getMovie(CSVRecord record) throws ParseException {
        Movie movie = new Movie();
        movie.id = Integer.parseInt(record.get("id"));
        movie.title = record.get("title");
        movie.homepage = record.get("homepage");
        movie.originalTitle = record.get("original_title");
        movie.overview = record.get("overview");
        movie.language = record.get("original_language");
        movie.popularity = Double.parseDouble(record.get("popularity"));

        String releaseDateString = record.get("release_date");
        if (null != releaseDateString && !"".equals(releaseDateString))
            movie.releaseDate = parseDate(releaseDateString);

        movie.genre = getGenres(record.get("genres"));
        movie.productionCompany = getProductionCompany(record.get("production_companies"));
        movie.spokenLanguage = getSpokenLanguages(record.get("spoken_languages"));
        movie.keyword = getKeywords(record.get("keywords"));

        return movie;
    }

    private static Date parseDate(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(dateStr);
    }

    private static List<ProductionCompany> getProductionCompany(String productionCompaniesString) {
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        JSONArray arr = new JSONArray(productionCompaniesString);
        for (int i=0; i<arr.length(); i++) {
            JSONObject genreJson = arr.getJSONObject(i);
            int id = genreJson.getInt("id");
            String name = genreJson.getString("name");
            productionCompanies.add(new ProductionCompany(id, name));
        }
        return productionCompanies;
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

    private static List<Keyword> getKeywords(String keywordString) {
        List<Keyword> keywords = new ArrayList<>();
        JSONArray arr = new JSONArray(keywordString);
        for (int i=0; i<arr.length(); i++) {
            JSONObject keywordJson = arr.getJSONObject(i);
            int id = keywordJson.getInt("id");
            String name = keywordJson.getString("name");
            keywords.add(new Keyword(id, name));
        }
        return keywords;
    }

    private static List<SpokenLanguage> getSpokenLanguages(String spokenLanguageString) {
        List<SpokenLanguage> spokenLanguages = new ArrayList<>();
        JSONArray arr = new JSONArray(spokenLanguageString);
        for (int i=0; i<arr.length(); i++) {
            JSONObject spokenLanguageJson = arr.getJSONObject(i);
            SpokenLanguage spokenLanguage = new SpokenLanguage();

            spokenLanguage.iso_639_1 = spokenLanguageJson.getString("iso_639_1");
            spokenLanguage.name = spokenLanguageJson.getString("name");
            spokenLanguages.add(spokenLanguage);
        }
        return spokenLanguages;
    }

    public static List<Genre> getAllGenres(Movie movie) {
        return new ArrayList<>(new HashSet<Genre>(movie.genre));
    }

    public static List<Genre> getAllGenres(List<Movie> movies) {
        Set genres = new HashSet<Genre>();
        for(Movie movie : movies){
            genres.addAll(getAllGenres(movie));
        }

        return new ArrayList<>(genres);
    }

    public static List<ProductionCompany> getAllProductionCompanies(List<Movie> movies) {
        Set genres = new HashSet<ProductionCompany>();
        for(Movie movie : movies){
            genres.addAll(movie.productionCompany);
        }

        return new ArrayList<>(genres);
    }
}