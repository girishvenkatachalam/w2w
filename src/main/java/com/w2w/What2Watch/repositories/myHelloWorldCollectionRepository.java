package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.myHelloWorldCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface myHelloWorldCollectionRepository extends MongoRepository<myHelloWorldCollection,String> {

    public List<myHelloWorldCollection> findAll();
}
