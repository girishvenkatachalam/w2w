package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.SpokenLanguage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LanguageRepository extends MongoRepository<SpokenLanguage,String> {
}
