package com.ghar.ghar.controller;

import com.ghar.ghar.entity.Review;
import com.ghar.ghar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties/{propertyId}/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getPropertyReviews(@PathVariable Long propertyId) {
        List<Review> reviews = reviewService.getPropertyReviews(propertyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> addReviewToProperty(
            @PathVariable Long propertyId,
            @RequestBody Review review) {
        Review addedReview = reviewService.addReviewToProperty(propertyId, review);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long propertyId,
            @PathVariable Long reviewId,
            @RequestBody Review updatedReview) {
        Review updated = reviewService.updateReview(propertyId, reviewId, updatedReview);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long propertyId,
            @PathVariable Long reviewId) {
        reviewService.deleteReview(propertyId, reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
