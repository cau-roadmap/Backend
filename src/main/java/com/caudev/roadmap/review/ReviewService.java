package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import com.caudev.roadmap.restaurant.RestaurantRepository;
import com.caudev.roadmap.restaurant.RestaurantResponseDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public Page<Review> findAllReviews(Pageable pageable){
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        return reviewPage;
    }

    public Page<Review> searchByContent(String keyword, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findWithSearchCond(keyword,pageable);
        return reviewPage;
    }

    public Page<Review> findReviewByRestaurant(Long restaurant_id) throws NotFoundException {
        Optional<Restaurant> find = restaurantRepository.findById(restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found : restaurant");
        Page<Review> reviewPage = reviewRepository.findAllByRestaurant(find.get());
        return reviewPage;
    }

    public void deleteReview(Long review_id) throws NotFoundException{
        Optional<Review> find = reviewRepository.findById(review_id);
        if(find.isEmpty())
            throw new NotFoundException("not found : review");
        reviewRepository.deleteById(review_id);
    }

    public ReviewResponseDto createReviewResponse(Review review){
        ReviewResponseDto reviewResponseDto = modelMapper.map(review,ReviewResponseDto.class);

        return reviewResponseDto;
    }
}
