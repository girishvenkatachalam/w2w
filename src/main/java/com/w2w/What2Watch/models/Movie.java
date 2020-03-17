package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

public class Movie {

    @Id
    public String _id;

    public int id;
    public String homepage;
    public String originalTitle;
    public String overview;
    public String title;
}
