package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.Exception.GenreNotFoundException;
import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("genres")
    @ResponseStatus(HttpStatus.OK)
    public List<Genre> getGenres() throws GenreNotFoundException {
        return genreService.getGenres();
    }

}
