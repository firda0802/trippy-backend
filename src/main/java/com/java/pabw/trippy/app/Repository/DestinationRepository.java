package com.java.pabw.trippy.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.Destination;

import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer>{
    Optional<Destination> findById(Integer destinationId);

}
