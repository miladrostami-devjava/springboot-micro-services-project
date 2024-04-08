package com.microservice.reviewms.review.controller;



import com.microservice.reviewms.review.messaging.ReviewMessageProducer;
import com.microservice.reviewms.review.model.Review;
import com.microservice.reviewms.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
private final ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }



    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                        @RequestBody Review review){
    boolean isReviewSaved =    reviewService.addReview(review,companyId);
if (isReviewSaved){
    reviewMessageProducer.sendMessage(review);
    return new ResponseEntity<>("Review Added Successfully!",HttpStatus.CREATED);

}else {
    return new ResponseEntity<>("Review Not Saved!",HttpStatus.NOT_FOUND);
}


    }


@GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updatedReview(@PathVariable Long reviewId,
                                                @RequestBody Review review){
        boolean isReviewedUpdated = reviewService.updateReview( reviewId, review);
        if (isReviewedUpdated){
            return new ResponseEntity<>("Reviewed Updated Successfully!",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Reviewed Not Updated !",HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
boolean isDeletedReviewed = reviewService.deleteReview(reviewId);
if (isDeletedReviewed){
    return new ResponseEntity<>("Review Deleted Successfully!",HttpStatus.OK);
} else {
    return new ResponseEntity<>("Review Not Deleted! ",HttpStatus.NOT_FOUND);
}
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){

List<Review> reviewList = reviewService.getAllReviews(companyId);
return reviewList
        .stream()
        .mapToDouble(Review::getRating)
        .average()
        .orElse(0.0);
    }






}
