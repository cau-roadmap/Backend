package com.caudev.roadmap.place;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam("keyword") String keyword,
                                 @PageableDefault(size = 10,sort = "lastModified",
            direction = Sort.Direction.DESC) Pageable pageable,
                                 PagedResourcesAssembler<Place> assembler) {
        //검색의 경우는 잘못된 값을 집어넣으면 그냥 그거에 따라 결과가안나옴
        //굳이 에러처리 필요 x
        //sort는 직접 넣어보기 sort=~ & dir=desc
        Page<Place> place = placeService.search(keyword, pageable);
        PagedModel<EntityModel<PlaceResponseDto>> model =
                assembler.toModel(place, p -> PlaceResource.modelOf(placeService.createPlaceResponse(p)));
//        PagedModel<EntityModel<PlaceResponseDto>> result = placeService.addLinksWithSearch(model);
        return ResponseEntity.ok(model);
    }

}
