package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversation_participants")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConversationParticipantJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationJpaEntity conversation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    private LocalDateTime joinedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        joinedAt = LocalDateTime.now();
    }
}