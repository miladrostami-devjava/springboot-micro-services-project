package com.microservice.jobmicroservice.job.service.impl;


import com.microservice.jobmicroservice.job.clients.CompanyClient;
import com.microservice.jobmicroservice.job.clients.ReviewClient;
import com.microservice.jobmicroservice.job.dto.JobDTO;
import com.microservice.jobmicroservice.job.external.Company;
import com.microservice.jobmicroservice.job.external.Review;
import com.microservice.jobmicroservice.job.jobMapper.JobMapper;
import com.microservice.jobmicroservice.job.model.Job;
import com.microservice.jobmicroservice.job.repository.JobRepository;
import com.microservice.jobmicroservice.job.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobs;

    @Autowired
    private final RestTemplate restTemplate;

    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    int attempt = 0;


    public JobServiceImpl(JobRepository jobs, RestTemplate restTemplate, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobs = jobs;
        this.restTemplate = restTemplate;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

  /*  @Override
    public List<Job> findAll() {
        List<Job> jobList = jobs.findAll();
        List<JobDTO> jobWithCompanies = new ArrayList<>();

        for (Job job : jobList) {
            JobDTO jobWithCompany = new JobDTO();
            jobWithCompany.setJob(job);

            RestTemplate restTemplate = new RestTemplate();
            Company company = restTemplate.getForObject("http://localhost:8081/companies/1"
                    + job.getCompanyId(),
                    Company.class );
            jobWithCompany.setCompany(company);
            jobWithCompanies.add(jobWithCompany);
        }


//
//        System.out.println("COMPANY :" + " " + company.getName());
//        System.out.println("COMPANY :" + " " + company.getId());
        return jobs.findAll();
    }*/

/*    @Override
    public List<JobDTO> findAll() {
        List<Job> jobList = jobs.findAll();
        List<JobDTO> jobWithCompanies = new ArrayList<>();

        for (Job job : jobList) {
            JobDTO jobWithCompany = new JobDTO();
            jobWithCompany.setJob(job);

            RestTemplate restTemplate = new RestTemplate();
            Company company = restTemplate.getForObject("http://localhost:8081/companies/1"
                            + job.getCompanyId(),
                    Company.class);
            jobWithCompany.setCompany(company);
            jobWithCompanies.add(jobWithCompany);
        }


//
//        System.out.println("COMPANY :" + " " + company.getName());
//        System.out.println("COMPANY :" + " " + company.getId());
        return jobWithCompanies;
    }*/


    @Override
  //  @CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
//@Retry(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker")


    public List<JobDTO> findAll() {
        System.out.println("Attempt :" + ++attempt);
        List<Job> jobList = jobs.findAll();
        List<JobDTO> jobWithCompanies = new ArrayList<>();
        return jobList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
}

    private JobDTO convertToDTO(Job job) {

      /*  JobDTO jobWithCompany = new JobDTO();
        jobWithCompany.setJob(job);
        */
     //   COMPANYMS
      //  RestTemplate restTemplate = new RestTemplate();
      /*  Company company = restTemplate.getForObject("http://localhost:8081/companies/"
                        + job.getCompanyId(),
                Company.class);*/

    /*    Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/"
                        + job.getCompanyId(),
                Company.class);*/
        Company company = companyClient.getCompany(job.getCompanyId()) ;



      /*  ResponseEntity<List<Review>> reviewResponse  =
        restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId()
           , HttpMethod.GET
           , null
           , new ParameterizedTypeReference<List<Review>>() {
           });
List<Review> reviews = reviewResponse.getBody();*/

   List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

JobDTO jobDTO = JobMapper.mapJobToJobWithCompanyDTO(job,company,reviews);

     /*   jobWithCompany.setCompany(company);
        return jobWithCompany;*/
       // jobDTO.setCompany(company);
        return jobDTO;


    }


    @Override
    public void createJob(Job job) {
        //    job.setId(nextId);
        jobs.save(job);
    }

/*    @Override
    public Job getById(Long id) {
        return jobs.findById(id).orElse(null);
    }*/
@Override
public JobDTO getById(Long id) {
    Job job = jobs.findById(id).orElse(null);
    return convertToDTO(job);
}




    @Override
    public boolean deleteJob(Long id) {
        try {
            jobs.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job jobUpdate) {
        Optional<Job> jobOptional = jobs.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(jobUpdate.getTitle());
            job.setDescription(jobUpdate.getDescription());
            job.setMinSalary(job.getMinSalary());
            job.setMaxSalary(jobUpdate.getMaxSalary());
            job.setLocation(jobUpdate.getLocation());
            jobs.save(job);
            return true;
        }

        return false;
    }


}
