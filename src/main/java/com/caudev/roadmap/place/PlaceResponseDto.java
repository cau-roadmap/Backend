package com.caudev.roadmap.place;

import com.caudev.roadmap.spot.SpotRepository;
import com.caudev.roadmap.spot.SpotResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponseDto {

    private Long id;

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private Long phoneNum;

    private SpotResponseDto spotResponseDto;

}
