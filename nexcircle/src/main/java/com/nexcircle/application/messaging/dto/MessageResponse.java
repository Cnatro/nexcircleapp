package com.nexcircle.application.messaging.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    UUID id;
    UUID senderId;
    String content;
    String messageType;
    UUID parentMessageId; // reply
    LocalDateTime createdAt;
}
