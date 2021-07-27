package com.caudev.roadmap.place;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PlaceRepositoryExtension {
    Page<Place> findWithSearchCond(String keyword, Pageable pageable);
}
