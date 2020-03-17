package com.w2w.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
    public int id;
    public long budget;
    public List<Genre> genres = new ArrayList<>();
    public String homepage;
    public List<Keyword> keywords = new ArrayList<>();
    public OriginalLanguage originalLanguage;
    public String originalTitle;
    public String overview;
    public float popularity;
    public List<ProductionCompany> productionCompanies = new ArrayList<>();
    public Date releaseDate;
    public long revenue;
    public int runtime;
    public Status status;
    public String tagLine;
    public String title;
    public float voteAverage;
    public int voteCount;
    public List<ProductionCountry> productionCountries = new ArrayList<>();
    public List<SpokenLanguage> spokenLanguages = new ArrayList<>();
}
