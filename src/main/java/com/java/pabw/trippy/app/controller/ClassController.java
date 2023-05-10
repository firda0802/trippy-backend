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
import com.java.pabw.trippy.app.Repository.ClassRepository;
import com.java.pabw.trippy.app.dto.Messages;
import com.java.pabw.trippy.app.models.ClassSeats;
import com.java.pabw.trippy.app.service.CrudService;
import com.java.pabw.trippy.app.service.InitializeService;
import com.java.pabw.trippy.app.utillity.HttpUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/class")
@Slf4j
public class ClassController {

    @Autowired
    InitializeService initializeService;

    @Autowired
    CrudService classService;
    private final ObjectMapper mapper = new ObjectMapper();

    // list class seats
    @GetMapping(value = "/get-list")
    public ResponseEntity<Messages> listClass(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);
        Messages resp = new Messages();
        resp.success();
        resp.setData(initializeService.listClass());
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(resp));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    // add class seats
    @PostMapping(value = "/add-update-class")
    public ResponseEntity<Messages> addUpdateClass(@RequestBody ReqUpdateClass req,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(req));
        log.info(writeLog);
        boolean status = classService.updateAddClass(req);
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

    // delete class seats
    @DeleteMapping(value = "/delete/{classId}")
    public ResponseEntity<Messages> deleteClass(@PathVariable(name = "classId") Integer classId,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString(classId));
        log.info(writeLog);
        Messages resp = new Messages();
        Boolean status = classService.deleteClassSeats(classId);
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