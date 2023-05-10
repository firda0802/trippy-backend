package com.java.pabw.trippy.app.controller;
import org.springframework.web.bind.annotation.*;
import com.java.pabw.trippy.app.Repository.SeatsRepository;
import com.java.pabw.trippy.app.models.Seats;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/seats")
public class SeatsController {
    @Autowired
    private SeatsRepository seatsRepository;

    // Get all seats (admin and mitra only)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public List<Seats> getAllSeats() {
        return seatsRepository.findAll();
    }

    // Get seat by id (admin and mitra only)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Seats getSeatById(@PathVariable Long id) {
        return seatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));
    }

    // Add new seat (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Seats addSeat(@RequestBody Seats seatObj) {
        return seatsRepository.save(seatObj);
    }

    // Update seat (admin and mitra only)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Seats updateSeat(@PathVariable Long id, @RequestBody Seat seatObjDetails) {
        Seats seatObj = seatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seatObj.setSeatsNumber(seatObjDetails.getSeatsNumber());
        seatObj.setSeatsGroup(seatObjDetails.getSeatsGroup());
        seatObj.setPositions(seatObjDetails.getPositions());
        seatObj.setClassId(seatObjDetails.getClassId());
        seatObj.setAirplaneId(seatObjDetails.getAirplaneId());

        return seatsRepository.save(seatObj);
    }

    // Delete seat (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSeat(@PathVariable Long id) {
        Seats seatObj = seatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seatsRepository.delete(seatObj);
    }
}
