package com.nexcircle.infrastructure.persistence.messaging.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ConversationListItemProjection {
    UUID getId();
    String getType();
    String getName();
    String getAvatar();

    UUID getConversationParticipantId();
    UUID getUserId();
    String getFullName();
    String getUsername();
    String getAvatarUrl();
    Boolean getIsOnline();

    UUID getMessageId();
    UUID getSenderId();
    String getContent();
    String getMessageType();
    LocalDateTime getCreatedAt();
    UUID getParentMessageId();
}
