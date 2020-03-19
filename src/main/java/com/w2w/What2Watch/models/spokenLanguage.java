package com.w2w.What2Watch.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "language")
public class spokenLanguage {

    @Id
    String _id;
    String isoNumber;
    String name;

    public spokenLanguage(String isoNumber, String name) {
        this.isoNumber = isoNumber;
    }


    public String getIsoNumber() {
        return isoNumber;
    }

    public void setIsoNumber(String isoNumber) {
        this.isoNumber = isoNumber;
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
        spokenLanguage that = (spokenLanguage) o;
        return Objects.equals(isoNumber, that.isoNumber) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoNumber, name);
    }
}
