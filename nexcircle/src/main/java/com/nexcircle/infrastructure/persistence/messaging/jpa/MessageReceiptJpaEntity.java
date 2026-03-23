package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message_receipts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageReceiptJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private MessageJpaEntity message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    private LocalDateTime readAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}