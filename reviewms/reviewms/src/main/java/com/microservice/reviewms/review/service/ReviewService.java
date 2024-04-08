package com.microservice.reviewms.review.service;


import com.microservice.reviewms.review.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
boolean addReview(Review review,Long companyId);
Review getReview(Long reviewId);
boolean updateReview(Long reviewId,Review updatedReview);
    boolean deleteReview(Long reviewId);



}
