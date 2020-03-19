package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.Service.MovieService;
import com.w2w.What2Watch.exceptions.LanguageNotFoundException;
import com.w2w.What2Watch.models.Movie;
import com.w2w.What2Watch.models.SpokenLanguage;
import com.w2w.What2Watch.services.LanguageService;
import com.w2w.What2Watch.utils.PreferenceVsMoviesMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie-details")
public class DashboardController {

    @Autowired
    private MovieService movieService;

    @Autowired
    LanguageService languageService;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ResponseEntity getMovieDetails(Principal principal, ModelMap modelMap)
    {
        List<PreferenceVsMoviesMap> preferenceVsMoviesMap = new ArrayList<>();

        Page<Movie> movies = movieService.getTrendingMovies();
        preferenceVsMoviesMap.add(new PreferenceVsMoviesMap("Trending", movies.toList()));

        List<String> genrePreference = new ArrayList<>();
        genrePreference.add("Action");
        genrePreference.add("Thriller");
        genrePreference.add("Drama");
        HashMap<String, List<Movie>> moviesByGenrePreference = movieService.getMoviesByPreference(genrePreference);

        for(Map.Entry<String, List<Movie>> map : moviesByGenrePreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(map.getKey(), map.getValue()));
        }

        List<SpokenLanguage> languages = new ArrayList<>();
        try {
            languages = languageService.getLanguages();
        }
        catch(LanguageNotFoundException ex){};

        HashMap<String, String> languageMap = new HashMap<String, String>();
        for(SpokenLanguage sl : languages)
        {
            languageMap.put(sl.getIso_639_1(), sl.getLanguageName());
        }

        List<String> languagePreference = new ArrayList<>();
        languagePreference.add("en");
        languagePreference.add("fr");
        HashMap<String, List<Movie>> moviesByLanguagePreference = movieService.getMoviesByLanguagePreference(languagePreference);

        for(Map.Entry<String, List<Movie>> map : moviesByLanguagePreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(languageMap.get(map.getKey()), map.getValue()));
        }

        List<String> productionCompanyPreference = new ArrayList<>();
        productionCompanyPreference.add("Warner Bros.");
        productionCompanyPreference.add("Lightstorm Entertainment");
        HashMap<String, List<Movie>> moviesByCompanyPreference = movieService.getMoviesByProductionCompanyPreference(productionCompanyPreference);

        for(Map.Entry<String, List<Movie>> map : moviesByCompanyPreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(map.getKey(), map.getValue()));
        }

//        Page<Movie> moviesByLanguagePreference = movieService.getMoviesByLanguagePreference();
//        preferenceVsMoviesMap.add(new PreferenceVsMoviesMap("Language", moviesByLanguagePreference.toList()));
//
//        Page<Movie> moviesByProductionCompanyPreference = movieService.getMoviesByProductionCompanyPreference();
//        preferenceVsMoviesMap.add(new PreferenceVsMoviesMap("Production Company", moviesByProductionCompanyPreference.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(preferenceVsMoviesMap);
    }
}

