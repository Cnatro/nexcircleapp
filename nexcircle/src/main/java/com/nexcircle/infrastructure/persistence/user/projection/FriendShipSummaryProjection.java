package com.nexcircle.infrastructure.persistence.user.projection;

import java.util.UUID;

public interface FriendShipSummaryProjection {
    UUID getId();
    UUID getUserId();
    String getFullName();
    String getAvatar();
}
