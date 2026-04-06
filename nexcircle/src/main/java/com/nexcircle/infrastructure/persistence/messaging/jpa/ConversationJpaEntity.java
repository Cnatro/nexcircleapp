package com.nexcircle.infrastructure.persistence.messaging.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConversationJpaEntity {

    @Id
    private UUID id;

    private String name;
    private String avatar;

    private String type;
    private UUID lastMessageId;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}