package com.ust.stock_notification.controller;

import com.ust.stock_notification.model.Notification;
import com.ust.stock_notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/save")
    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification){
        return ResponseEntity.ok().body(notificationService.saveNotification(notification));
    }

    @GetMapping("/get-all-notifications")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return ResponseEntity.ok().body(notificationService.getAllNotifications());
    }

    @GetMapping("/get-notification-by-id/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable(name="id") String id){
        Optional<Notification> notificationOptional = notificationService.getNotificationById(id);
        if(notificationOptional.isPresent()){
            return ResponseEntity.ok().body(notificationOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-notification-by-id/{id}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(notificationService.deleteNotificationById(id));
    }
    @PutMapping("/update-notification/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable(name="id") String id,@RequestBody Notification notification){
        Notification updatedNotification = notificationService.updateNotification(id,notification);
        if(updatedNotification == null){
            return ResponseEntity.ok().body("Notification not found");
        }
        else{
            return ResponseEntity.ok().body(updatedNotification);
        }
    }
    @GetMapping("/get-threshold/{stockId}/{username}")
    public String getThreshold(@PathVariable String stockId, @PathVariable String username) {
        return notificationService.getThresholdForStockAndUser(stockId, username);
    }
}
