package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Movie {

    @Id
    public String _id;

    public int id;
    public String homepage;
    public String originalTitle;
    public String overview;
    public String title;
    public String language;
    public String image;
    public double popularity;

    public List<Genre> genre;
    public List<ProductionCompany> productionCompany;
    public List<SpokenLanguage> spokenLanguage;
    public List<Keyword> keyword;
    public Date releaseDate;

    public Movie() {
        this.genre = new ArrayList<>();
        this.productionCompany = new ArrayList<>();
    }
}
