package com.nexcircle.infrastructure.persistence.messaging.jpa;

import com.nexcircle.domain.messaging.entity.Conversation;
import com.nexcircle.infrastructure.persistence.user.jpa.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageJpaEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationJpaEntity conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserJpaEntity sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String messageType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private MessageJpaEntity parentMessage;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (status == null) status = "sent";
        createdAt = LocalDateTime.now();
    }
}