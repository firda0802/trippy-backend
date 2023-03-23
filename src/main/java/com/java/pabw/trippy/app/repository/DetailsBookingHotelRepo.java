package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.DetailsBookingHotel;

import org.springframework.stereotype.Repository;

@Repository
public interface DetailsBookingHotelRepo extends JpaRepository<DetailsBookingHotel, Integer> {
    Optional<DetailsBookingHotel> findById(Integer idBookingDetails);
    
}
