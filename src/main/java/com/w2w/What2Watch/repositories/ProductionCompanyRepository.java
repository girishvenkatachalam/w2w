package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.ProductionCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionCompanyRepository extends MongoRepository<ProductionCompany,String> {
}
