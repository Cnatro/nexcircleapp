package com.nexcircle.application.messaging.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateConversation {
    UUID conversationId;
    UUID lastMessageId;
    String avatar;
    String name;
}
