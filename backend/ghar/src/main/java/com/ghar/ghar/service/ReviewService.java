package com.ghar.ghar.service;

import com.ghar.ghar.entity.Review;
import com.ghar.ghar.exception.CustomException;
import com.ghar.ghar.repository.PropertyRepository;
import com.ghar.ghar.repository.ReviewRepository;
import com.ghar.ghar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Review> getPropertyReviews(Long propertyId) {
        if(propertyRepository.existsById(propertyId)) {
            return reviewRepository.findByPropertyId(propertyId);
        }else{
            throw new CustomException("Property not found with ID: "+propertyId);
        }
    }

    public Review addReviewToProperty(Long propertyId, Review review) {
        if(propertyRepository.existsById(propertyId) && propertyRepository.existsById(review.getProperty().getId())) {
            if(userRepository.existsById(review.getUser().getId())){
                review.getProperty().setId(propertyId);
                return reviewRepository.save(review);
            }else{
                throw new CustomException("Owner details are invalid with ID: "+review.getUser().getId());
            }
        }else{
            throw new CustomException("Property not found with ID: "+propertyId);
        }
    }

    public Review updateReview(Long propertyId, Long reviewId, Review updatedReview) {
        if(reviewRepository.existsById(reviewId) && propertyRepository.existsById(propertyId) && propertyRepository.existsById(updatedReview.getProperty().getId())) {
            if(userRepository.existsById(updatedReview.getUser().getId())) {
                Review existingReview = reviewRepository.findByIdAndPropertyId(reviewId, propertyId);
                existingReview.setRating(updatedReview.getRating());
                existingReview.setReviewText(updatedReview.getReviewText());
                return reviewRepository.save(existingReview);
            }else{
                throw new CustomException("Owner details are invalid with ID: "+updatedReview.getUser().getId());
            }
        }else {
            throw  new CustomException("Review not found for propertyId: " + propertyId + " and reviewId: " + reviewId);
        }
    }

    public void deleteReview(Long propertyId, Long reviewId) {
        if(reviewRepository.existsById(reviewId) && propertyRepository.existsById(propertyId)) {
            Review existingReview = reviewRepository.findByIdAndPropertyId(reviewId, propertyId);
            reviewRepository.delete(existingReview);
        }else {
           throw  new CustomException("Review not found for propertyId: " + propertyId + " and reviewId: " + reviewId);
        }
    }
}
