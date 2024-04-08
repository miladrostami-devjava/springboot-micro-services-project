package com.microservice.jobmicroservice.job.service;


import com.microservice.jobmicroservice.job.dto.JobDTO;
import com.microservice.jobmicroservice.job.model.Job;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();
    void createJob(Job job);

//    Job getById(Long id);
JobDTO getById(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job jobUpdate);
}
