package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.GenreNotFoundException;
import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getGenres() throws GenreNotFoundException {
        List<Genre> genres=genreRepository.findAll();
        if(genres == null) {
            throw new GenreNotFoundException("Genres are not present");
        }
        return  genres;
    }
}
