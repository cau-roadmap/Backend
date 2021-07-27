package com.caudev.roadmap.spot;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/spots")
@RequiredArgsConstructor
public class SpotController {

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
}
