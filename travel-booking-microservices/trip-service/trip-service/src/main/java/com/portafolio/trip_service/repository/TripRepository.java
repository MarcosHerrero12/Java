package com.portafolio.trip_service.repository;

import com.portafolio.trip_service.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByUsername(String username);

    Optional<Trip> findByIdAndUsername(Long id, String username);



}
