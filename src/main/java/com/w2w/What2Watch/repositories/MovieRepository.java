package com.w2w.What2Watch.repositories;

import com.w2w.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie,String> {
}
