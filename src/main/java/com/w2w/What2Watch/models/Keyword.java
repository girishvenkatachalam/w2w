package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

public class Keyword {
    @Id
    public String _id;

    public int id;
    public String name;

    public Keyword(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
