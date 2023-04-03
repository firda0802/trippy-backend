package com.java.pabw.trippy.app.controller;

import com.java.pabw.trippy.app.models.Airport;
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
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    // Get all airports
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Get airport by id
    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));
    }

    // Add new airport
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Airport addAirport(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    // Update airport
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));

        airport.setName(airportDetails.getName());
        airport.setAddress(airportDetails.getAddress());

        return airportRepository.save(airport);
    }

    // Delete airport
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAirport(@PathVariable Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id: " + id));

        airportRepository.delete(airport);
    }
}
