package com.microservice.jobmicroservice.job.repository;

import com.microservice.jobmicroservice.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
