package com.shop.review.controller;

import com.shop.common.model.Review;
import com.shop.review.controller.dto.ReviewDto;
import com.shop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.addReview(Review.builder()
                .authorName(cleanContent(reviewDto.authorName()))
                .content(cleanContent(reviewDto.content()))
                .productId(reviewDto.productId())
                .build());
    }

    private String cleanContent(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}
