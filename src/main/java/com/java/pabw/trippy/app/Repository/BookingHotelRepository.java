package com.java.pabw.trippy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.pabw.trippy.app.entity.BookingHotel;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingHotelRepository extends JpaRepository<BookingHotel, Integer>{
    Optional<BookingHotel> findById(Integer idBooking);
}
