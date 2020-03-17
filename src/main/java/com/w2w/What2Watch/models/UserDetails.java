package com.w2w.What2Watch.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
public class UserDetails {
    @Id
    private String id;
    private String emailId;
    private String name;
    private String pictureURL;

    public UserDetails(String id, String emailId, String name) {
        this.id = id;
        this.emailId = emailId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "userDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailID='" + emailId + '\'' +
                '}';
    }
}
