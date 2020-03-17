package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Id;

public class Genre {
    @Id
    String _id;

    public int id;
    public String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
