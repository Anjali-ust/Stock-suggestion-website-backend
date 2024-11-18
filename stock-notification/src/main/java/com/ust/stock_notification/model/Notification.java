package com.ust.stock_notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {
    @Id
    private String notificationId;
    private String threshold;
    private String stockId;
    private String username;

    public Notification() {
    }

    public Notification(String notificationId, String threshold, String stockId, String username) {
        this.notificationId = notificationId;
        this.threshold = threshold;
        this.stockId = stockId;
        this.username = username;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
