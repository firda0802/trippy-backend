package com.java.pabw.trippy.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.DetailsBookingHotel;

import org.springframework.stereotype.Repository;

@Repository
public interface DetailsBookingHotelRepository extends JpaRepository<DetailsBookingHotel, Integer> {
    Optional<DetailsBookingHotel> findById(Integer idBookingDetails);
    
}
