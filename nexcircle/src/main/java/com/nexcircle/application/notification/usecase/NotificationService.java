package com.nexcircle.application.notification.usecase;

import com.nexcircle.application.notification.dto.NotificationFirebaseDto;
import com.nexcircle.domain.notification.entity.Notification;
import com.nexcircle.domain.notification.repository.NotificationRepository;
import com.nexcircle.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService {
    NotificationRepository notificationRepository;

    public void createNotification(NotificationFirebaseDto dto){

        Notification noti = Notification.builder()
                .title(dto.getTitle())
                .user(User.builder().id(dto.getUserId()).build())
                .type(dto.getType().name())
                .isRead(false)
                .content(dto.getBody())
                .build();
        this.notificationRepository.save(noti);
    }

    public void updateReadNotification(UUID notiId){
        Notification notification = this.notificationRepository.findById(notiId)
                .orElseThrow( () -> new RuntimeException("Not found notification" + notiId));

        notification.setIsRead(true);

        this.notificationRepository.save(notification);
    }
}
