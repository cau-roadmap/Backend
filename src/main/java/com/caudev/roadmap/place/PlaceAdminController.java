package com.caudev.roadmap.place;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/places")
@RequiredArgsConstructor
public class PlaceAdminController {

    private final PlaceService placeService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }


    @GetMapping
    public ResponseEntity findAllPlaces(@PageableDefault(size = 10) Pageable pageable, PagedResourcesAssembler<Place> assembler){
        Page<Place> places = placeService.findAllPlaces(pageable);
        PagedModel<EntityModel<PlaceResponseDto>> model =
                assembler.toModel(places, p -> PlaceResource.modelOf(placeService.createPlaceResponse(p)));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/search")
    public ResponseEntity findByName(@RequestParam("keyword") String keyword,
                                  @PageableDefault(size = 10) Pageable pageable,
                                  PagedResourcesAssembler<Place> assembler){
        //이전 버전
//        return placeService.findPlaceByName(place_name,pageable);

        // search 적용
        //검색의 경우는 잘못된 값을 집어넣으면 그냥 그거에 따라 결과가안나옴
        //굳이 에러처리 필요 x
        //sort는 직접 넣어보기 sort=~ & dir=desc
        Page<Place> place = placeService.search(keyword, pageable);
        PagedModel<EntityModel<PlaceResponseDto>> model =
                assembler.toModel(place, p -> PlaceResource.modelOf(placeService.createPlaceResponse(p)));
//        PagedModel<EntityModel<PlaceResponseDto>> result = placeService.addLinksWithSearch(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{place_id}")
    public ResponseEntity findById(@PathVariable Long place_id) throws NotFoundException{

        Place place = placeService.findPlaceById(place_id);
        EntityModel<PlaceResponseDto> model = PlaceResource.modelOf(placeService.createPlaceResponse(place));
        return ResponseEntity.ok(model);

    }

    @DeleteMapping("/{place_id}")
    public ResponseEntity deletePlace (@PathVariable Long place_id) throws  NotFoundException{
        placeService.deletePlace(place_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createPlace (@RequestBody PlaceDto placeDto) throws NotFoundException {
        Place place = placeService.createPlace(placeDto);
        EntityModel<PlaceResponseDto> model = PlaceResource.modelOf(placeService.createPlaceResponse(place));
        return ResponseEntity.ok(model);

    }

    @PutMapping("/{place_id}")
    public ResponseEntity modifyPlace (@PathVariable Long place_id, @RequestBody PlaceDto placeDto) throws NotFoundException {
        Place place = placeService.modifyPlace(place_id,placeDto);
        PlaceResponseDto placeResponseDto = placeService.createPlaceResponse(place);
        EntityModel<PlaceResponseDto> placeResponse = PlaceResource.modelOf(placeResponseDto);
        return ResponseEntity.ok(placeResponse);
    }
}
