package com.nexcircle.infrastructure.persistence.user.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface FriendRequestSummaryProjection {
    UUID getId();
    UUID getSenderId();
    String getSenderName();
    String getSenderAvatar();
    LocalDateTime getCreatedAt();
}
