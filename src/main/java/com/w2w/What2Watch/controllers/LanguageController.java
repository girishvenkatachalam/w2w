package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.exceptions.LanguageNotFoundException;
import com.w2w.What2Watch.models.spokenLanguage;
import com.w2w.What2Watch.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class LanguageController  {

    @Autowired
    LanguageService languageService;

    @GetMapping("languages")
    @ResponseStatus(HttpStatus.OK)
    public List<spokenLanguage> getLanguages() throws LanguageNotFoundException {
        return languageService.getLanguages();
    }

}
