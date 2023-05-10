package com.java.pabw.trippy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.pabw.trippy.app.Repository.BookingHotelRepository;
import com.java.pabw.trippy.app.models.BookingHotel;

@RestController
@RequestMapping("/booking")
public class BookingHotelController {

    @Autowired
    private BookingHotelRepository bookingHotelRepository;

    // Get all bookings
    @GetMapping
    public List<BookingHotel> getAllBookings() {
        return bookingHotelRepository.findAll();
    }

    // Get booking by id
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Integer id) {
        return bookingHotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    // Add new booking
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Booking addBooking(@RequestBody Booking bookingObj) {
        return bookingHotelRepository.save(bookingObj);
    }

    // Update booking
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingObj) {
        Booking booking = bookingHotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Update booking details
        booking.setInvoiceNo(bookingObj.getInvoiceNo());
        booking.setUserId(bookingObj.getUserId());
        booking.setRoomId(bookingObj.getRoomId());

        return bookingHotelRepository.save(booking);
    }

    // Delete booking
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteBooking(@PathVariable Long id) {
        Booking booking = bookingHotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        bookingHotelRepository.deleteById(booking);
    }

}
