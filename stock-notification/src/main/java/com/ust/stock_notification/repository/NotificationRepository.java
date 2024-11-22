package com.ust.stock_notification.repository;

import com.ust.stock_notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {
    Optional<Notification> findByStockIdAndUsername(String stockId, String username);

	List<Notification> findByUsername(String username);
}
