package com.nexcircle.domain.messaging.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conversation {
    private UUID id;
    private String type;
    private UUID lastMessageId;
    private LocalDateTime createdAt;
}