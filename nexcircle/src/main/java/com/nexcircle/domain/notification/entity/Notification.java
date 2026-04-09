package com.nexcircle.domain.notification.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
    private UUID id;
    private User user;
    private String type;
    private String title;
    private String content;
    private Boolean isRead;
    private LocalDateTime createdAt;
}