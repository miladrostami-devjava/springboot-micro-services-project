package com.microservice.reviewms.review.service.impl;


import com.microservice.reviewms.review.model.Review;
import com.microservice.reviewms.review.reopository.ReviewRepository;
import com.microservice.reviewms.review.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview( Long reviewId) {

        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review updatedReview) {
Review review = reviewRepository.findById(reviewId).orElse(null);
if (review != null){
    review.setTitle(updatedReview.getTitle());
    review.setDescription(updatedReview.getDescription());
    review.setRating(updatedReview.getRating());
    review.setCompanyId(updatedReview.getCompanyId());
     reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }


}
