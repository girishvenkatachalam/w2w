package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.exceptions.ProductionCompanyNotFoundException;
import com.w2w.What2Watch.models.ProductionCompany;
import com.w2w.What2Watch.services.ProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductionCompanyController {

    @Autowired
    ProductionCompanyService productionCompanyService;

    @GetMapping("productionCompanies")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductionCompany> getProductionCompanies() throws ProductionCompanyNotFoundException {
        return productionCompanyService.getProductionCompanies();
    }

}
