package com.java.pabw.trippy.app.controller;
package com.java.pabw.trippy.app.repository.AirplaneRepository;

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
@RequestMapping("/airplane")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping
    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    @GetMapping("/{id}")
    //admin and partner only
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Airplane getAirplaneById(@PathVariable Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));
    }

    @PostMapping
    public Airplane addAirplane(@RequestBody Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    //admin and partner only
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Airplane updateAirplane(@PathVariable Long id, @RequestBody Airplane airplaneDetails) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));

        airplane.setType(airplaneDetails.getType());
        airplane.setAirportId(airplaneDetails.getAirportId());

        return airplaneRepository.save(airplane);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public void deleteAirplane(@PathVariable Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));

        airplaneRepository.delete(airplane);
    }

}
