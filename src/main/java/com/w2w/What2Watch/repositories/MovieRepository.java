package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie,String> {
    public List<Movie> findAll(Sort sort);
}
