package com.java.pabw.trippy.app.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pabw.trippy.app.Repository.AirportRepository;
import com.java.pabw.trippy.app.dto.Messages;
import com.java.pabw.trippy.app.models.Airport;
import com.java.pabw.trippy.app.service.CrudService;
import com.java.pabw.trippy.app.service.InitializeService;
import com.java.pabw.trippy.app.utillity.HttpUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/airport")
@Slf4j
public class AirportController {

    @Autowired
    CrudService airportService;

    @Autowired
    InitializeService initializeService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/get-list")
    public ResponseEntity<Messages> listAirport(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);
        Messages resp = new Messages();
        resp.success();
        resp.setData(initializeService.getAirport());
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping(value = "/add-update-airport")
    public ResponseEntity<Messages> addUpdateairport(@RequestBody ReqCreateAirport req,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(req));
        log.info(writeLog);
        airportService.updateAirport(req.getId(), req.getName(), req.getAddress(), req.getCityId());
        Messages resp = new Messages();
        resp.success();
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @DeleteMapping(value = "/delete/{airportId}")
    public ResponseEntity<Messages> deleteAirport(@PathVariable(name = "airportId") Integer airportId,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(airportId));
        log.info(writeLog);
        Messages resp = new Messages();
        Boolean status = airportService.deleteAirport(airportId);
        if (Boolean.TRUE.equals(status)) {
            resp.success();
        } else {
            resp.notFound();
        }
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }
}
