package com.voguevista.service;
import org.springframework.stereotype.Service;
import com.voguevista.dto.ReviewRequest;
import com.voguevista.entity.Review;
import com.voguevista.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(ReviewRequest request){
        Review review = new Review();
        review.setUserId(request.getUserId());
        review.setPackageId(request.getPackageId());
        review.setReview(request.getReview());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    public List<Review> getPackageReviews(Integer packageId){
        return reviewRepository.findByPackageId(packageId);
    }
    public List<Review> getUserReviews(Integer userId){
        return reviewRepository.findByUserId(userId);
    }
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
    public void deleteReview(Integer id){
        reviewRepository.deleteById(id);
    }

}
