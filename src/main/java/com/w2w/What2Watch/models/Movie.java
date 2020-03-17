package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

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
    public double popularity;

    public List<Genre> genre;
    public List<ProductionCompany> productionCompany;
}
