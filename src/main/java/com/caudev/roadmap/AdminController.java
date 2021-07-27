package com.caudev.roadmap;


import com.caudev.roadmap.spot.Spot;
import com.caudev.roadmap.spot.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SpotService spotService;

    @GetMapping
    public ResponseEntity getSpotList(){
        List<Spot> spotList = spotService.findAllSpots();
        return ResponseEntity.ok(spotList);
    }
}
