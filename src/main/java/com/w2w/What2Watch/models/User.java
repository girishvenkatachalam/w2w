package com.w2w.What2Watch.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Data
@Document(collection = "user")
public class User {


    private String userId;
    private String name;
    private String email;
    private String password;
    private List<String> genres;
    private List<String> languages;
    private List<String> production_companies;

    public User() {

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getGeneres() {
        return genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getProduction_companies() {
        return production_companies;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGeneres(List<String> generes) {
        this.genres = generes;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setProduction_companies(List<String> production_companies) {
        this.production_companies = production_companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email);
    }
}
