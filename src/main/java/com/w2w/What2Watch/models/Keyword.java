package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Keyword {
    @Id
    public String _id;

    public int id;
    public String name;

    public Keyword(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return id == keyword.id &&
                Objects.equals(_id, keyword._id) &&
                Objects.equals(name, keyword.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, id, name);
    }
}
