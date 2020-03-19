package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie,String> {
    public List<Movie> findAll(Sort sort);

    @Query(value = "{ 'genre.name' : ?0 }")
    List<Movie> findByGenre(String genre, PageRequest pageRequest);


    @Query(value = "{ 'productionCompany.name' : ?0 }")
    List<Movie> findByProductionCompany(String companyName, PageRequest pageRequest);

    List<Movie> findByLanguage(String language, PageRequest pageRequest);

}
