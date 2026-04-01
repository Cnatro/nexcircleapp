package com.nexcircle.domain.user.entity;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Friendship {
    private UUID id;
    private User user1;
    private User user2;
    private String status;
    private LocalDateTime createdAt;
}