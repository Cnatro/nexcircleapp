package com.nexcircle.domain.user.repository;

import com.nexcircle.domain.user.entity.Friendship;
import com.nexcircle.infrastructure.persistence.user.projection.FriendShipSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository {
    Friendship save(Friendship friendship);
    Page<FriendShipSummaryProjection> findAllByUserId(UUID userId, Pageable pageable);
    boolean isExistsFriendship(UUID user1Id, UUID user2Id);
}
