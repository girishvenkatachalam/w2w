package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.exceptions.LanguageNotFoundException;
import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.*;
import com.w2w.What2Watch.services.LanguageService;
import com.w2w.What2Watch.services.MovieService;
import com.w2w.What2Watch.services.UserService;
import com.w2w.What2Watch.utils.PreferenceVsMoviesMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/movie-details")
public class DashboardController {

    @Autowired
    private MovieService movieService;

    @Autowired
    LanguageService languageService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ResponseEntity getMovieDetails(Principal principal) throws UserNotFoundException {
        String emailID = getUserEmailID(principal);
        User user = userService.getUserByGivenEmail(emailID);;
        List<Genre> genres = user.getGenres();
        List<SpokenLanguage> spokenLanguages= user.getLanguages();
        List<ProductionCompany> productionCompanies= user.getProduction_companies();

        List<PreferenceVsMoviesMap> preferenceVsMoviesMap = filPreferenceVsMovieMapByTrending();

        fillPreferenceVsMovieMapByGenre(genres, preferenceVsMoviesMap);

        fillPreferenceVsMovieMapByLanguage(spokenLanguages, preferenceVsMoviesMap);

        fillPreferenceVsMovieMapByProductionCompany(productionCompanies, preferenceVsMoviesMap);

        return ResponseEntity.status(HttpStatus.OK).body(preferenceVsMoviesMap);
    }

    protected String getUserEmailID(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        Map<String, String> details = (Map<String, String>) authentication.getDetails();
        return details.get("email");
    }

    private List<PreferenceVsMoviesMap> filPreferenceVsMovieMapByTrending() {
        List<PreferenceVsMoviesMap> preferenceVsMoviesMap = new ArrayList<>();
        List<Movie> movies = movieService.getTrendingMovies();
        preferenceVsMoviesMap.add(new PreferenceVsMoviesMap("Trending", movies));
        return preferenceVsMoviesMap;
    }

    private void fillPreferenceVsMovieMapByProductionCompany(List<ProductionCompany> productionCompanies, List<PreferenceVsMoviesMap> preferenceVsMoviesMap) {
        List<String> productionCompanyPreference = new ArrayList<>();
        for (ProductionCompany productionCompany : productionCompanies) {
            productionCompanyPreference.add(productionCompany.name);
        }

        HashMap<String, List<Movie>> moviesByCompanyPreference = movieService.getMoviesByProductionCompanyPreference(productionCompanyPreference);

        for(Map.Entry<String, List<Movie>> map : moviesByCompanyPreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(map.getKey(), map.getValue()));
        }
    }

    private void fillPreferenceVsMovieMapByLanguage(List<SpokenLanguage> spokenLanguages, List<PreferenceVsMoviesMap> preferenceVsMoviesMap) {
        List<SpokenLanguage> languages = new ArrayList<>();
        try {
            languages = languageService.getLanguages();
        }
        catch(LanguageNotFoundException ex){}
        ;

        HashMap<String, String> languageMap = new HashMap<String, String>();
        for(SpokenLanguage sl : languages)
        {
            languageMap.put(sl.getIso_639_1(), sl.getLanguageName());
        }

        List<String> languagePreference = new ArrayList<>();
        for (SpokenLanguage spokenLanguage : spokenLanguages) {
            languagePreference.add(spokenLanguage.iso_639_1);
        }

        HashMap<String, List<Movie>> moviesByLanguagePreference = movieService.getMoviesByLanguagePreference(languagePreference);

        for(Map.Entry<String, List<Movie>> map : moviesByLanguagePreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(languageMap.get(map.getKey()), map.getValue()));
        }
    }

    private void fillPreferenceVsMovieMapByGenre(List<Genre> genres, List<PreferenceVsMoviesMap> preferenceVsMoviesMap) {
        List<String> genrePreference = new ArrayList<>();
        for (Genre genre : genres) {
            genrePreference.add(genre.name);
        }

        HashMap<String, List<Movie>> moviesByGenrePreference = movieService.getMoviesByGenrePreference(genrePreference);

        for(Map.Entry<String, List<Movie>> map : moviesByGenrePreference.entrySet()) {
            preferenceVsMoviesMap.add(new PreferenceVsMoviesMap(map.getKey(), map.getValue()));
        }
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieByGivenId(@PathVariable("movieId") @NotEmpty String movieId) throws UserNotFoundException {
        return movieService.getMovieByGivenId(movieId);
    }

    @GetMapping(value="/search/{searchTerm}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> searchMovie(@PathVariable("searchTerm") @NotEmpty String searchTerm,Principal principal)
    {
        List<Movie> searchResult = new ArrayList<>();
        if (!searchTerm.isEmpty() || !("".equals(searchTerm.trim()))) {
            searchTerm = searchTerm.toLowerCase();
            List<Movie> titleSearch = new ArrayList<>();
            List<Movie> similarTitleSearch = new ArrayList<>();
            List<Movie> keywordSearch = new ArrayList<>();
            titleSearch = movieService.getMovieByTitle(searchTerm);
            similarTitleSearch = movieService.getMovieSimilarToTitle(searchTerm);
            keywordSearch = movieService.getMoviesByKeyword(searchTerm);
            if (titleSearch != null && titleSearch.size() != 0)
                searchResult.addAll(titleSearch);
            if (similarTitleSearch != null && similarTitleSearch.size() != 0)
                searchResult.addAll(similarTitleSearch);
            if (keywordSearch != null && keywordSearch.size() != 0)
                searchResult.addAll(keywordSearch);
            return searchResult;
        }
        return searchResult;
    }
}

