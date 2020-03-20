package com.w2w.What2Watch.services;

import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
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
    public void testGetTrendingMovies()
    {

        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(new Movie());
        moviesList.add(new Movie());
        moviesList.add(new Movie());

        when(movieRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<Movie>(moviesList,
                PageRequest.of(0, 4, Sort.Direction.DESC, "popularity"), moviesList.size()));

        List<Movie> movies = movieService.getTrendingMovies();
        assertEquals(3, movies.size());
        verify(movieRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    public void testGetSingleMovie()
    {
        when(movieRepository.findById("123")).thenReturn(java.util.Optional.ofNullable(movie));
        assertEquals(123,movie.id);

    }

    @Test
    public void testGetMoviesByPreferenceReturnsEmptyIfNoPreferenceIsPassed()
    {
        HashMap<String, List<Movie>> movieMap = movieService.getMoviesByGenrePreference(new ArrayList<>());
        assertEquals(0, movieMap.size());
        verify(movieRepository, times(0)).findByGenre(anyString(),any(PageRequest.class));
    }

    @Test
    public void testGetMoviesByPreferenceReturnsEmptyIfMovieRepositoryCouldntFindByGenre()
    {
        when(movieRepository.findByGenre(eq("Action"), any(PageRequest.class))).thenReturn(new ArrayList<>());

        List<String> preferenceList = new ArrayList<>();
        preferenceList.add("Action");
        HashMap<String, List<Movie>> movieMap = movieService.getMoviesByGenrePreference(preferenceList);
        assertEquals(0, movieMap.size());
        verify(movieRepository, times(1)).findByGenre(eq("Action"),any(PageRequest.class));
    }

    @Test
    public void testGetMoviesByPreference()
    {
        List<Movie> actionList = new ArrayList<>();
        actionList.add(new Movie());
        actionList.add(new Movie());

        List<Movie> dramaList = new ArrayList<>();
        dramaList.add(new Movie());
        dramaList.add(new Movie());

        when(movieRepository.findByGenre(eq("Action"), any(PageRequest.class))).thenReturn(actionList);
        when(movieRepository.findByGenre(eq("Drama"), any(PageRequest.class))).thenReturn(dramaList);

        List<String> preferenceList = new ArrayList<>();
        preferenceList.add("Action");
        preferenceList.add("Drama");
        HashMap<String, List<Movie>> movieMap = movieService.getMoviesByGenrePreference(preferenceList);
        assertEquals(2, movieMap.size());

        assertTrue(movieMap.containsKey("Action"));
        assertTrue(movieMap.containsKey("Drama"));

        assertEquals(actionList, movieMap.get("Action"));
        assertEquals(dramaList, movieMap.get("Drama"));

        verify(movieRepository, times(1)).findByGenre(eq("Action"),any(PageRequest.class));
        verify(movieRepository, times(1)).findByGenre(eq("Drama"),any(PageRequest.class));
    }
}
