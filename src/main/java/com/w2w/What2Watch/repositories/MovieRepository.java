package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.Keyword;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String>, MongoRepository<Movie, String> {
    public List<Movie> findAll(Sort sort);

    @Query(value = "{ 'genre.name' : ?0 }")
    List<Movie> findByGenre(String genre, PageRequest pageRequest);


    @Query(value = "{ 'productionCompany.name' : ?0 }")
    List<Movie> findByProductionCompany(String companyName, PageRequest pageRequest);

    @Query(value = "{ 'spokenLanguage.iso_639_1' : ?0 }")
    List<Movie> findBySpokenLanguage(String spokenLanguage, PageRequest pageRequest);

    public Movie findById(int id);

    List<Movie> findByTitle(String movieTitle);

    List<Movie> findByTitleIgnoreCase(String movieTitle);

    List<Movie> findByTitleLikeIgnoreCase(String movieTitle);

    @Query(value = "{ 'keyword.name' : ?0 }")
    List<Movie> findByKeyword(String keyword);
}
