package com.nexcircle.domain.user.repository;

import com.nexcircle.domain.user.entity.FriendRequest;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestInfoProjection;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FriendRequestRepository {
    FriendRequest save(FriendRequest friendRequest);
    FriendRequestInfoProjection updateAndReturn(UUID id);
    Page<FriendRequestSummaryProjection> findAllByStatusAndReceiverId(String status, UUID receiverId, Pageable pageable);
}
