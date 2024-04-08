package com.microservice.jobmicroservice.job.dto;

import com.microservice.jobmicroservice.job.external.Company;
import com.microservice.jobmicroservice.job.external.Review;

import java.util.List;

public class JobDTO {
//   private Job job;
private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
   private Company company;
//   private Review review;
private List<Review> reviews;
 /*   public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

   /* public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }*/

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
