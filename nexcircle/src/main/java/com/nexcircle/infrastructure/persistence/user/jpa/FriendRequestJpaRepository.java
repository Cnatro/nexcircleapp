package com.nexcircle.infrastructure.persistence.user.jpa;

import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestInfoProjection;
import com.nexcircle.infrastructure.persistence.user.projection.FriendRequestSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FriendRequestJpaRepository extends JpaRepository<FriendRequestJpaEntity, UUID> {
    @Query(value = """
        select
            fr.id as id,
            u.id as senderId,
            u.full_name as senderName,
            u.avatar_url as senderAvatar,
            fr.created_at as createdAt
        from friend_requests fr
        join users u on u.id = fr.sender_id
        where fr.status = :status
          and fr.receiver_id = :receiverId
        """,
            countQuery = """
        select count(*)
        from friend_requests fr
        where fr.status = :status
          and fr.receiver_id = :receiverId
        """,
            nativeQuery = true)
    Page<FriendRequestSummaryProjection> findAllByStatusAndReceiverId(String status, UUID receiverId, Pageable pageable);

    @Query(value = """
            UPDATE friend_requests
                 SET status = 'accepted'
                 WHERE id = :id
                 RETURNING sender_id as senderId, receiver_id as receiverId
            """, nativeQuery = true)
    FriendRequestInfoProjection updateAndReturn(UUID id);
}
