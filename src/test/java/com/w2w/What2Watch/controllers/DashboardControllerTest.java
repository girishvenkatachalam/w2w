package com.w2w.What2Watch.controllers;

import com.sun.net.httpserver.HttpPrincipal;
import com.w2w.What2Watch.exceptions.LanguageNotFoundException;
import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.SpokenLanguage;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.services.LanguageService;
import com.w2w.What2Watch.services.MovieService;
import com.w2w.What2Watch.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DashboardControllerTest {
    @InjectMocks
    DashboardControllerStub dashboardControllerStub;

    @Mock
    UserService userService;

    @Mock
    LanguageService languageService;

    @Mock
    MovieService movieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMovieDetails() throws UserNotFoundException, LanguageNotFoundException {
        User testUser = new User();
        when(userService.getUserByGivenEmail("abc@example.com")).thenReturn(testUser);

        List<Movie> trendingMovies = new ArrayList<>();
        trendingMovies.add(new Movie());
        trendingMovies.add(new Movie());
        when(movieService.getTrendingMovies()).thenReturn(trendingMovies);

        List<Movie> actionMovies = new ArrayList<>();
        actionMovies.add(new Movie());
        HashMap<String, List<Movie>> genreMovies = new HashMap<>();
        genreMovies.put("Action", actionMovies);
        List<String> genrePref = new ArrayList<>();
        genrePref.add("Action");
        when(movieService.getMoviesByGenrePreference(genrePref)).thenReturn(genreMovies);

        List<Movie> englishMovies = new ArrayList<>();
        englishMovies.add(new Movie());
        HashMap<String, List<Movie>> englishMovieMap = new HashMap<>();
        englishMovieMap.put("en", englishMovies);
        List<String> langpref = new ArrayList<>();
        langpref.add("English");
        when(movieService.getMoviesByLanguagePreference(langpref)).thenReturn(englishMovieMap);

        List<SpokenLanguage> englishLanguage = new ArrayList<>();
        englishLanguage.add(new SpokenLanguage("en", "English"));
        when(languageService.getLanguages()).thenReturn(englishLanguage);

        List<Movie> productionCompanyMovies = new ArrayList<>();
        productionCompanyMovies.add(new Movie());
        HashMap<String, List<Movie>> pcMovieMap = new HashMap<>();
        pcMovieMap.put("Warner Bros", productionCompanyMovies);
        List<String> companypref = new ArrayList<>();
        companypref.add("Warner Bros");
        when(movieService.getMoviesByProductionCompanyPreference(companypref)).thenReturn(pcMovieMap);

        Principal principal = new HttpPrincipal(" daf", "fdsf");
        ResponseEntity repsonse = dashboardControllerStub.getMovieDetails(principal);
        assertEquals(HttpStatus.OK,repsonse.getStatusCode());
    }

}

class DashboardControllerStub extends DashboardController
{
    @Override
    protected String getUserEmailID(Principal principal)
    {
        return "abc@example.com";
    }
}
