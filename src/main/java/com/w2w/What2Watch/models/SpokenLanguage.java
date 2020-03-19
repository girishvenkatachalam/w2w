package com.w2w.What2Watch.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "language")
public class SpokenLanguage {

    @Id
    public String _id;
    public String iso_639_1;
    public String name;

    public SpokenLanguage(){

    }

    public SpokenLanguage(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name=name;
    }


    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpokenLanguage that = (SpokenLanguage) o;
        return Objects.equals(iso_639_1, that.iso_639_1) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso_639_1, name);
    }
}
