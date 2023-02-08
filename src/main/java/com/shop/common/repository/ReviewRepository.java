package com.shop.common.repository;

import com.shop.common.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductIdAndModeratedIs(Long productId, boolean moderated);
}
