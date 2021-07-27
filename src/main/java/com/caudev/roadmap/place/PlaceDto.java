package com.caudev.roadmap.place;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlaceDto {

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private String phoneNum;

    private Long spot_id;
}
