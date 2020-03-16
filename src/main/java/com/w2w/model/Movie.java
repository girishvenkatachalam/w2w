package com.w2w.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
    int id;
    long budget;
    List<Genre> genres = new ArrayList<>();
    String homepage;
    List<Keyword> keywords = new ArrayList<>();
    OriginalLanguage originalLanguage;
    String originalTitle;
    String overview;
    float popularity;
    List<ProductionCompany> productionCompanies = new ArrayList<>();
    Date releaseDate;
    long revenue;
    int runtime;
    Status status;
    String tagLine;
    String title;
    float voteAverage;
    int voteCount;
    List<ProductionCountry> productionCountries = new ArrayList<>();
    List<SpokenLanguage> spokenLanguages = new ArrayList<>();
}
