package com.ust.stock_notification.service;

import com.ust.stock_notification.model.Notification;
import com.ust.stock_notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepo;

    public Notification saveNotification(Notification notification){
        return notificationRepo.save(notification);
    }

    public List<Notification> getAllNotifications(){
        return notificationRepo.findAll();
    }

    public Optional<Notification> getNotificationById(String id){
        return notificationRepo.findById(id);
    }

    public String deleteNotificationById(String id){
        Optional<Notification> notification = notificationRepo.findById(id);
        if(notification.isPresent()){
            notificationRepo.deleteById(id);
            return "Notification deleted";
        }
        else{
            return "Cannot find notification";
        }
    }

    public Notification updateNotification(String id,Notification notification){
        Optional<Notification> notificationOptional = notificationRepo.findById(id);
        if(notificationOptional.isPresent()){
            Notification existingNotification = notificationOptional.get();
            existingNotification.setNotificationId(notification.getNotificationId());
            existingNotification.setThreshold(notification.getThreshold());
            existingNotification.setStockId(notification.getStockId());
            existingNotification.setUsername(notification.getUsername());
            return notificationRepo.save(existingNotification);
        }
        return null;
    }

    public String getThresholdForStockAndUser(String stockId, String username) {
        Optional<Notification> notification = notificationRepo.findByStockIdAndUsername(stockId, username);
        return notification.map(Notification::getThreshold).orElse("Threshold not found");
    }
    
    public Notification updateThresholdByStockIdAndUsername(String stockId, String username, Notification notification) {
        // Retrieve the notification by stockId and username
        Notification existingNotification = notificationRepo.findByStockIdAndUsername(stockId, username).get();

        if (existingNotification == null) {
            return null; // No matching notification found
        }

        // Update the threshold value
        existingNotification.setThreshold(notification.getThreshold());

        // Save the updated notification
        return notificationRepo.save(existingNotification);
    }
    
    public List<Notification> getNotificationsByUsername(String username) {
        return notificationRepo.findByUsername(username);
    }
    

}
