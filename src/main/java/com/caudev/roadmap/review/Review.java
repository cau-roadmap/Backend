package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {

    @Id
    @GeneratedValue
    @Column(name="review_id")
    private Long id;

    private String title;

    private String writer;

    private Long star;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}
