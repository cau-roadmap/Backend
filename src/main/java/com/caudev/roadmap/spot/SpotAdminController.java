package com.caudev.roadmap.spot;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/spots")
@RequiredArgsConstructor
public class SpotAdminController {

    private final SpotService spotService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @GetMapping
    public ResponseEntity getSpotList(){
        List<Spot> spotList = spotService.findAllSpots();
        return ResponseEntity.ok(spotList);
    }

    @PostMapping
    public ResponseEntity createSpot(@RequestBody SpotDto spotDto){
        spotService.createSpot(spotDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{spot_id}")
    public ResponseEntity deleteSpot (@PathVariable Long spot_id) throws NotFoundException{
        spotService.deleteSpot(spot_id);
        return ResponseEntity.ok().build();
    }
}
