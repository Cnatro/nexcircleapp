package com.nexcircle.domain.notification.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDevice {
    private UUID id;
    private User user;
    private String deviceToken;
    private String platform;
    private LocalDateTime lastLogin;
}