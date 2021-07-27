package com.caudev.roadmap.review;

import com.caudev.roadmap.place.PlaceController;
import com.caudev.roadmap.place.PlaceDto;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ReviewResource {
    public static EntityModel<ReviewResponseDto> modelOf(ReviewResponseDto reviewResponseDto) {
        EntityModel<ReviewResponseDto> reivewResource = EntityModel.of(reviewResponseDto);
        reivewResource.add(linkTo(ReviewController.class).slash("search").withRel("search"));
        return reivewResource;
    }
}
