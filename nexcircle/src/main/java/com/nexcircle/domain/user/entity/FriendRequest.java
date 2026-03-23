package com.nexcircle.domain.user.entity;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter  @NoArgsConstructor @AllArgsConstructor @Builder
public class FriendRequest {
    private UUID id;
    private User sender;
    private User receiver;
    private String status;
    private LocalDateTime createdAt;
}