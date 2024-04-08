package com.microservice.jobmicroservice.job.jobMapper;

import com.microservice.jobmicroservice.job.dto.JobDTO;
import com.microservice.jobmicroservice.job.external.Company;
import com.microservice.jobmicroservice.job.external.Review;
import com.microservice.jobmicroservice.job.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapJobToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;

    }



}
