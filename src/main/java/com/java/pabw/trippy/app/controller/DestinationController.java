package com.java.pabw.trippy.app.controller;

import org.springframework.web.bind.annotation.*;
import com.java.pabw.trippy.app.DTO.Messages;
import com.java.pabw.trippy.app.DTO.ReqUpdatePayment;
import com.java.pabw.trippy.app.service.AdminService;
import com.java.pabw.trippy.app.utillity.HttpUtility;
import com.java.pabw.trippy.app.service.InitializeService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationRepository destinationRepository;

    // Get all destinations (admin and mitra only)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    // Get destination by id (admin and mitra only)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Destination getDestinationById(@PathVariable Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found with id: " + id));
    }

    // Add new destination (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Destination addDestination(@RequestBody Destination destination) {
        return destinationRepository.save(destination);
    }

    // Update destination (admin and mitra only)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Destination updateDestination(@PathVariable Long id, @RequestBody Destination destinationDetails) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found with id: " + id));

        destination.setFrom(destinationDetails.getFrom());
        destination.setTo(destinationDetails.getTo());
        destination.setAirplane(destinationDetails.getAirplane());

        return destinationRepository.save(destination);
    }

    // Delete destination (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDestination(@PathVariable Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found with id: " + id));

        destinationRepository.delete(destination);
    }

}
