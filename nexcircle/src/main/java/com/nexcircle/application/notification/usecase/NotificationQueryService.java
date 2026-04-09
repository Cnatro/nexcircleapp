package com.nexcircle.application.notification.usecase;

import com.nexcircle.application.notification.dto.NotificationFilter;
import com.nexcircle.domain.notification.entity.Notification;
import com.nexcircle.domain.notification.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationQueryService {
    NotificationRepository notificationRepository;

    public Page<Notification> getAllNotificationByUser(UUID uuid, NotificationFilter filter){
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("createdAt").descending());

        return this.notificationRepository.findAllByUser(uuid, pageable);
    }
}
