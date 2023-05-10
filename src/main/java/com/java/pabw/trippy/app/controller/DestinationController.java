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
import com.java.pabw.trippy.app.Repository.DestinationRepository;
import com.java.pabw.trippy.app.dto.Messages;
import com.java.pabw.trippy.app.models.Destination;
import com.java.pabw.trippy.app.service.CrudService;
import com.java.pabw.trippy.app.service.InitializeService;
import com.java.pabw.trippy.app.utillity.HttpUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dest")
@Slf4j
public class DestinationController {

    @Autowired
    InitializeService initializeService;

    @Autowired
    CrudService destinationService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/get-list")
    public ResponseEntity<Messages> listDestination(HttpServletRequest httpServletRequest)
            throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);
        Messages resp = new Messages();
        resp.success();
        resp.setData(initializeService.getDestination());
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping(value = "/add-update")
    public ResponseEntity<Messages> addUpdateDestination(@RequestBody ReqUpdateDestination req,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(req));
        log.info(writeLog);
        boolean status = destinationService.updateAddDestination(req);
        Messages resp = new Messages();
        if (status) {
            resp.success();
        } else {
            resp.notFound();
        }

        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @DeleteMapping(value = "/delete/{destId}")
    public ResponseEntity<Messages> deleteClass(@PathVariable(name = "destId") Integer destId,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(destId));
        log.info(writeLog);
        Messages resp = new Messages();
        Boolean status = destinationService.deleteDestination(destId);
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