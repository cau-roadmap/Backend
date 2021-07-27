package com.caudev.roadmap.place;

import com.caudev.roadmap.spot.Spot;
import com.caudev.roadmap.spot.SpotRepository;
import com.caudev.roadmap.spot.SpotResponseDto;
import com.caudev.roadmap.spot.SpotService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final SpotRepository spotRepository;
    private final ModelMapper modelMapper;
    private final SpotService spotService;

    public Page<Place> findAllPlaces(Pageable pageable){
        Page<Place> placePage = placeRepository.findAll(pageable);
        return placePage;
    }

    public Place createPlace(PlaceDto placeDto) throws NotFoundException{

        Place place = new Place();
        modelMapper.map(placeDto,place);
        Optional<Spot> spotOpt = spotRepository.findById(placeDto.getSpot_id());
        if(spotOpt.isEmpty())
            throw new NotFoundException("not found : spot");
        place.setSpot(spotOpt.get());
        place = placeRepository.save(place);
        return place;

    }

    public Place findPlaceById(Long place_id) throws NotFoundException {
        Optional<Place> place = placeRepository.findById(place_id);
        if(place.isEmpty())
            throw new NotFoundException("not found : place");
        return place.get();
    }

    //현재 사용안함
//    public Page<Place> findPlaceByName(String place_name,Pageable pageable) {
//        Page<Place> places = placeRepository.findByName(place_name,pageable);
//        return places;
//    }

    public void deletePlace(Long place_id) throws NotFoundException{
        Optional<Place> find = placeRepository.findById(place_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        placeRepository.deleteById(place_id);
    }

    public Place modifyPlace(Long place_id, PlaceDto placeDto) throws  NotFoundException {
        Optional<Place> findPlace = placeRepository.findById(place_id);
        Optional<Spot> findSpot = spotRepository.findById(placeDto.getSpot_id());
        if(findPlace.isEmpty())
            throw new NotFoundException("not found : place");
        if(findSpot.isEmpty())
            throw new NotFoundException("not found : spot");
        placeRepository.deleteById(place_id);
        Place place = new Place();
        modelMapper.map(placeDto,place);
        place.setSpot(findSpot.get());
        return placeRepository.save(place);
        //delete 사용 안 하고 mapper 만 써서 id 값 유지 가능할까

    }

    public PlaceResponseDto createPlaceResponse(Place place){
        PlaceResponseDto placeResponseDto = modelMapper.map(place,PlaceResponseDto.class);
        SpotResponseDto spotResponseDto = spotService.createSpotResponseDto(place.getSpot());
        placeResponseDto.setSpotResponseDto(spotResponseDto);

        return placeResponseDto;
    }

    public Page<Place> search(String keyword,Pageable pageable) {
        Page<Place> place = placeRepository.findWithSearchCond(keyword,pageable);
        return place;
    }
}
