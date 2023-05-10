package com.java.pabw.trippy.app.controller;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.pabw.trippy.app.Repository.NotificationRepository;
import com.java.pabw.trippy.app.models.Notifications;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get all notifications
    @GetMapping
    public List<Notifications> getAllNotifications() {
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
        notification.setUser(notificationDetails.getUserData());

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
