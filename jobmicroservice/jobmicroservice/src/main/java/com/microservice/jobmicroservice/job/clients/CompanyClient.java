package com.microservice.jobmicroservice.job.clients;

import com.microservice.jobmicroservice.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service",
url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
Company getCompany(@PathVariable(value = "id") Long id);

}
