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
public class ConversationResponse {
    UUID id;
    String type; // private or group
    UUID lastMessageId;
    LocalDateTime createdAt;
}
