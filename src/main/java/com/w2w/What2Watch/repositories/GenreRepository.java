package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre,String> {
}
