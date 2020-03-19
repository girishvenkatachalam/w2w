package com.w2w.What2Watch.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Document(collection = "user")
public class User {


    private String userId;
    private String name;
    private String email;
    private String pictureUrl;
    private List<String> genres;
    private List<String> languages;
    private List<String> production_companies;

    public User() {

    }

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.genres = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.production_companies = new ArrayList<>();
    }

    public User(String userId, String name, String email,String pictureUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.genres = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.production_companies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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

    public List<String> getGenres() {
        return genres;
    }

    public String getUserId() {
        return userId;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
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
