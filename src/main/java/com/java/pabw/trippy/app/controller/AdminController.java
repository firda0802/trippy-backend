package com.java.pabw.trippy.app.controller;

import com.java.pabw.trippy.app.dto.Messages;
import com.java.pabw.trippy.app.dto.ReqUpdatePayment;
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

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    InitializeService initService;

    @Autowired
    AdminService adminService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/list-payment")
    public ResponseEntity<Messages> listPayment(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);

        Messages resp = new Messages();
        resp.success();
        resp.setData(initService.listPayment());
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(new Messages(resp.getResponseCode(), resp.getResponseMessage())));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping(value = "/create-payment")
    public ResponseEntity<Messages> createPayment(@RequestBody ReqUpdatePayment req, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);

        Messages resp = new Messages();
        adminService.createPayment(req);
        resp.success();
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(new Messages(resp.getResponseCode(), resp.getResponseMessage())));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @PutMapping(value = "/update-payment/{idPayment}")
    public ResponseEntity<Messages> updatePayment(@PathVariable(name = "idPayment") int idPayment, @RequestBody ReqUpdatePayment req, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);

        Messages resp = new Messages();
        boolean update = adminService.updatePayment(idPayment, req);
        if (update) {
            resp.success();
        } else {
            resp.notFound();
        }
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(new Messages(resp.getResponseCode(), resp.getResponseMessage())));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

    @DeleteMapping(value = "/disable-payment/{idPayment}")
    public ResponseEntity<Messages> disablePayment(@PathVariable(name = "idPayment") int idPayment, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String writeLog = HttpUtility.writeLogRequest(httpServletRequest, mapper.writeValueAsString("-"));
        log.info(writeLog);

        Messages resp = new Messages();
        adminService.disablePayment(idPayment);
        resp.success();
        String writeLogResp = HttpUtility.writeLogResp(mapper.writeValueAsString(new Messages(resp.getResponseCode(), resp.getResponseMessage())));
        log.info(writeLogResp);
        return ResponseEntity.ok().body(resp);
    }

}
