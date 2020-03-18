package com.w2w.What2Watch;

import com.w2w.What2Watch.Service.MovieService;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.repositories.MovieRepository;
import org.apache.http.protocol.HTTP;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestGetAllMovies()
    {
        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(new Movie());
        moviesList.add(new Movie());
        moviesList.add(new Movie());

        Page<Movie> page = new PageImpl<Movie>(moviesList.subList(0, 2), PageRequest.of(0, 2, Sort.Direction.DESC, "id"), moviesList.size());
        when(movieRepository.findAll(any(PageRequest.class))).thenReturn(page);
        ResponseEntity responseEntity = movieService.getAllMovies();

        assertEquals(page, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(movieRepository,times(1)).findAll(any(PageRequest.class));
    }
}
