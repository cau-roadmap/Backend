package com.caudev.roadmap.place;

import com.caudev.roadmap.spot.Spot;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Place {

    @Id
    @GeneratedValue
    @Column(name="place_id")
    private Long id;

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private Long phoneNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

}
