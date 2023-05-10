package com.java.pabw.trippy.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.models.BookingHotel;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingHotelRepository extends JpaRepository<BookingHotel, Integer>{
    Optional<BookingHotel> findById(Integer idBooking);
}
