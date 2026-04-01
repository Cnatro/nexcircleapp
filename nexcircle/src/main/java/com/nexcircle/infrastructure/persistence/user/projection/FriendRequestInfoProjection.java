package com.nexcircle.infrastructure.persistence.user.projection;

import java.util.UUID;

public interface FriendRequestInfoProjection {
    UUID getSenderId();
    UUID getReceiverId();
}
