package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.Destination;

import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepo extends JpaRepository<Destination, Integer>{
    Optional<Destination> findById(Integer destinationId);

}
