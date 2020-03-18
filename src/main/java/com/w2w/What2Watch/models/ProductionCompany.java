package com.w2w.What2Watch.models;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class ProductionCompany {

    @Id
    public String _id;

    public int id;
    public String name;

    public ProductionCompany(int id, String name) {

        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionCompany that = (ProductionCompany) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
