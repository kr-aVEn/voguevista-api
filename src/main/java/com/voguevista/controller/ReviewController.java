package com.voguevista.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.voguevista.dto.ReviewRequest;
import com.voguevista.entity.Review;
import com.voguevista.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.voguevista.repository.ReviewRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
private ReviewRepository reviewRepository;
  @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.addReview(request));
    }
    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<Review>> getPackageReviews(@PathVariable Integer packageId) {
        return ResponseEntity.ok(reviewService.getPackageReviews(packageId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Integer userId) {
        return ResponseEntity.ok(reviewService.getUserReviews(userId));
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteReview(
        @PathVariable Integer id,
        @RequestParam Integer userId) {   // ← who is trying to delete?

    Review review = reviewRepository.findById(id).orElse(null);

    if (review == null) {
        return ResponseEntity.notFound().build();
    }
    if (!review.getUserId().equals(userId)) {
        return ResponseEntity.status(403).body("You can only delete your own reviews");
    }

    reviewService.deleteReview(id);
    return ResponseEntity.ok("Review deleted successfully");
}
    }
