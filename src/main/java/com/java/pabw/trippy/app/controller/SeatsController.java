package com.java.pabw.trippy.app.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/seats")
public class SeatsController {
    @Autowired
    private SeatRepository seatRepository;

    // Get all seats (admin and mitra only)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    // Get seat by id (admin and mitra only)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Seat getSeatById(@PathVariable Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));
    }

    // Add new seat (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Seat addSeat(@RequestBody Seat seatObj) {
        return seatRepository.save(seatObj);
    }

    // Update seat (admin and mitra only)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Seat updateSeat(@PathVariable Long id, @RequestBody Seat seatObjDetails) {
        Seat seatObj = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seatObj.setSeatsNumber(seatObjDetails.getSeatsNumber());
        seatObj.setSeatsGroup(seatObjDetails.getSeatsGroup());
        seatObj.setPositions(seatObjDetails.getPositions());
        seatObj.setClassId(seatObjDetails.getClassId());
        seatObj.setAirplaneId(seatObjDetails.getAirplaneId());

        return seatRepository.save(seatObj);
    }

    // Delete seat (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSeat(@PathVariable Long id) {
        Seat seatObj = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seatRepository.delete(seatObj);
    }
}
