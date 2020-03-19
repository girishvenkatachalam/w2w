package com.w2w.What2Watch.services;

import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;

    Movie movie;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.id = 123;
    }

    @Test
    public void TestGetAllMovies()
    {
        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(new Movie());
        moviesList.add(new Movie());
        moviesList.add(new Movie());
    }

    @Test
    public void TestGetSingleMovie()
    {
        when(movieRepository.findById("123")).thenReturn(java.util.Optional.ofNullable(movie));
        assertEquals(123,movie.id);

    }
}
