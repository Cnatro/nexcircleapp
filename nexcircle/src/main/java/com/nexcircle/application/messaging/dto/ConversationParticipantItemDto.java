package com.nexcircle.application.messaging.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationParticipantItemDto {
    UUID id;
    UUID userId;
    String fullName;
    String userName;
    String avatarUrl;
    Boolean isOnline;
}
