package com.caudev.roadmap.spot;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final ModelMapper modelMapper;

    public List<Spot> findAllSpots(){
        return spotRepository.findAll();
    }

    public void createSpot(SpotDto spotDto) {
        Spot spot = new Spot();
        modelMapper.map(spot,spotDto);
        spotRepository.save(spot);
    }

    public void deleteSpot(Long spot_id) throws NotFoundException{
        if(spotRepository.findById(spot_id).isEmpty())
            throw new NotFoundException("not found : spot");
        spotRepository.deleteById(spot_id);
    }

    public SpotResponseDto createSpotResponseDto(Spot spot){
        SpotResponseDto spotResponseDto = modelMapper.map(spot,SpotResponseDto.class);
        return spotResponseDto;
    }

}
