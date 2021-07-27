package com.caudev.roadmap.place;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PlaceResource extends EntityModel<PlaceResponseDto> {
    public static EntityModel<PlaceResponseDto> modelOf(PlaceResponseDto placeResponseDto) {
        EntityModel<PlaceResponseDto> placeResource = EntityModel.of(placeResponseDto);
        placeResource.add(linkTo(PlaceController.class).slash("search").withRel("search"));
        return placeResource;
    }
}
