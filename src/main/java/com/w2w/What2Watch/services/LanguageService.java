package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.LanguageNotFoundException;
import com.w2w.What2Watch.models.SpokenLanguage;
import com.w2w.What2Watch.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public List<SpokenLanguage> getLanguages() throws LanguageNotFoundException {
        List<SpokenLanguage> languages=languageRepository.findAll();
        if(languages == null) {
            throw new LanguageNotFoundException("Genres are not present");
        }
        return  languages;
    }
}
