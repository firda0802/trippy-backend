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
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    // Get all classes (admin and mitra only)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    // Get class by id (admin and mitra only)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Class getClassById(@PathVariable Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));
    }

    // Add new class (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Class addClass(@RequestBody Class classObj) {
        return classRepository.save(classObj);
    }

    // Update class (admin and mitra only)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MITRA')")
    public Class updateClass(@PathVariable Long id, @RequestBody Class classObjDetails) {
        Class classObj = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));

        classObj.setName(classObjDetails.getName());
        classObj.setPrice(classObjDetails.getPrice());

        return classRepository.save(classObj);
    }

    // Delete class (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteClass(@PathVariable Long id) {
        Class classObj = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));

        classRepository.delete(classObj);
    }
}
