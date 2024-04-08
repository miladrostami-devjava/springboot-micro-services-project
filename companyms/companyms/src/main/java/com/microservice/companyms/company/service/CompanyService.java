package com.microservice.companyms.company.service;



import com.microservice.companyms.company.dto.ReviewMessage;
import com.microservice.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);


    void updateCompanyRating(ReviewMessage reviewMessage);
}
