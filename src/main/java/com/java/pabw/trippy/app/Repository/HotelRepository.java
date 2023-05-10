package com.java.pabw.trippy.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.Hotel;

import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
    Optional<Hotel> findById(Integer hotelId);
}
