package com.nexcircle.application.messaging.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationRequest {
    String type; // private or group
    UUID lastMessageId;
    List<UUID> userIds;
}
