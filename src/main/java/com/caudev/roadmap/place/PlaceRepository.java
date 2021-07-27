package com.caudev.roadmap.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface PlaceRepository extends JpaRepository<Place,Long>,PlaceRepositoryExtension {
    Page<Place> findAll(Pageable pageable);

    Page<Place> findByName(String name,Pageable pageable);
}
