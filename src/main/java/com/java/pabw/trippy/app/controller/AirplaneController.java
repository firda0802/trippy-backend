package com.java.pabw.trippy.app.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.java.pabw.trippy.app.Repository.AirplaneRepository;
import com.java.pabw.trippy.app.dto.AirplaneDTO;
import com.java.pabw.trippy.app.dto.Messages;
// import com.java.pabw.trippy.app.dto.Messages;
import com.java.pabw.trippy.app.models.Airplane;
// import lombok.extern.slf4j.Slf4j;
import com.java.pabw.trippy.app.service.CrudService;


// @Slf4j
@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    @Autowired
    CrudService crudService;

        @PostMapping("/add-airplane")
        public ResponseEntity<Messages> create(@RequestBody ReqCreateAirplane airplane){
            crudService.saveAirplane(airplane);
            Messages messages= new Messages();
            messages.setResponseCode(201);
            messages.setResponseMessage("Berhasil Ditambahkan");
            return ResponseEntity.ok().body(messages);
        }


        @GetMapping("/get-airplane")
        public ResponseEntity<Messages> get(){
            Messages messages= new Messages();
            messages.setResponseCode(200);
            messages.setResponseMessage("Berhasil Ditampilkan");
            messages.setData(crudService.findAllAirplanes());
            return ResponseEntity.ok().body(messages);
        }


        @PutMapping("/update-airplane/{airplaneId}")
        public ResponseEntity<Messages> update(@PathVariable Integer airplaneId, @RequestBody ReqCreateAirplane airplane){
            Messages messages= new Messages();
            Boolean status = crudService.updateAirplane(airplaneId, airplane);
            if (Boolean.TRUE.equals(status)) {
                crudService.updateAirplane(airplaneId, airplane);
                messages.setResponseCode(200);
                messages.setResponseMessage("Berhasil Diupdate");
            }
            else {
                messages.setResponseCode(204);
                messages.setResponseMessage("Data tidak ditemukan");
            }
            return ResponseEntity.ok().body(messages);
        }


        public ResponseEntity<Messages> delete(@PathVariable Integer airplaneId){
            Messages messages= new Messages();
            Boolean status = crudService.deleteAirplane(airplaneId);
            if (Boolean.TRUE.equals(status)) {
                crudService.deleteAirplane(airplaneId);
                messages.setResponseCode(200);
                messages.setResponseMessage("Berhasil Dihapus");
            }
            else {
                messages.setResponseCode(204);
                messages.setResponseMessage("Data tidak ditemukan");
            }
            return ResponseEntity.ok().body(messages);
        }

}
