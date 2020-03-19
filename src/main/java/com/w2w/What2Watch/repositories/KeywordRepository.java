package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.Keyword;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KeywordRepository extends MongoRepository<Keyword,String> {
}
