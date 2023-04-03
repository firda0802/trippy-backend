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

import javax.management.Notification;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by id
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    // Add new notification
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Notification addNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // Update notificatioc
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));

        notification.setContent(notificationDetails.getContent());
        notification.setDateNotification(notificationDetails.getDateNotification());
        notification.setStatus(notificationDetails.getStatus());
        notification.setNotificationCategory(notificationDetails.getNotificationCategory());
        notification.setUser(notificationDetails.getUser());

        return notificationRepository.save(notification);
    }

    // Delete notification
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteNotification(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));

        notificationRepository.delete(notification);
    }
}
