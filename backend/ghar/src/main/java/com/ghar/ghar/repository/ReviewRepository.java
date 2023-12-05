package com.ghar.ghar.repository;

import com.ghar.ghar.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r WHERE r.id = :reviewId AND r.property.id = :propertyId")
    Review findByIdAndPropertyId(Long reviewId, Long propertyId);

    List<Review> findByPropertyId(Long propertyId);
}
