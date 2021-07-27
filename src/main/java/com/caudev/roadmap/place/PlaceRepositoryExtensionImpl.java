package com.caudev.roadmap.place;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.caudev.roadmap.place.QPlace.place;
import static com.caudev.roadmap.spot.QSpot.spot;

public class PlaceRepositoryExtensionImpl extends QuerydslRepositorySupport implements PlaceRepositoryExtension{
    public PlaceRepositoryExtensionImpl() {
        super(Place.class);
    }

    @Override
    public Page<Place> findWithSearchCond(String keyword, Pageable pageable) {
        JPQLQuery<Place> query = from(place)
                .where(place.name.containsIgnoreCase(keyword))
                .leftJoin(place.spot, spot).fetchJoin()
                .distinct();
        JPQLQuery<Place> planQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Place> result = planQuery.fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
