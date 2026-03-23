package com.nexcircle.domain.messaging.entity;

import com.nexcircle.domain.user.entity.User;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConversationParticipant {
    private UUID id;
    private Conversation conversation;
    private User user;
    private LocalDateTime joinedAt;
}