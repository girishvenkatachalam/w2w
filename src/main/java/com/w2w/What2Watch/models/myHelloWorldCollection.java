package com.w2w.What2Watch.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.util.Date;

@Data
@Document(collection = "myHelloWorldCollection")
public class myHelloWorldCollection {

    @Id
    public String _id;

    @CreatedDate
    public Date DateCreated;

    public String MyDatas;

    public myHelloWorldCollection(){}

    public myHelloWorldCollection(String myData){
        this.MyDatas = myData;
    }

    public Date getDate_created() {
        return DateCreated;
    }

    public void setDate_created(Date date_created) {
        this.DateCreated = date_created;
    }

    public String getMyData() {
        return MyDatas;
    }

    public void setMyData(String myData) {
        this.MyDatas = myData;
    }

    @Override
    public String toString() {
        return "myHelloWorldCollection{" +
                "id='" + _id + '\'' +
                ", date_created='" + DateCreated + '\'' +
                ", myData='" + MyDatas + '\'' +
                '}';
    }
}
