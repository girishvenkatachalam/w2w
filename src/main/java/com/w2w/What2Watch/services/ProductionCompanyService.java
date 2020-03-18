package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.ProductionCompanyNotFoundException;
import com.w2w.What2Watch.models.ProductionCompany;
import com.w2w.What2Watch.repositories.ProductionCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCompanyService {
    @Autowired
    ProductionCompanyRepository productionCompanyRepository;

    public List<ProductionCompany> getProductionCompanies() throws ProductionCompanyNotFoundException {
        List<ProductionCompany> productionCompanies=productionCompanyRepository.findAll();
        if(productionCompanies == null) {
            throw new ProductionCompanyNotFoundException("Genres are not present");
        }
        return  productionCompanies;
    }
}
