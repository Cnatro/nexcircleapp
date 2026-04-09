package com.nexcircle.domain.notification.repository;

import com.nexcircle.domain.notification.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    Notification save(Notification notification);
    Optional<Notification> findById(UUID id);
    Page<Notification> findAllByUser(UUID id, Pageable pageable);
}
