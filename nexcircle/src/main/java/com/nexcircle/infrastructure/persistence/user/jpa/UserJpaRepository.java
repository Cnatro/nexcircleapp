package com.nexcircle.infrastructure.persistence.user.jpa;

import com.nexcircle.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByUsername(String username);

    @Query(value = """
                select *
                from users u
                join conversation_participants cp on cp.user_id = u.id
                where cp.conversation_id = :converId 
                  and cp.user_id <> :userSenderId
            """, nativeQuery = true)
    UserJpaEntity findUserReceiptMessageByConversationIdAndUserId(UUID converId, UUID userSenderId);

    @Query(value = """
            SELECT u.*
            FROM users u
            WHERE u.id <> :currentUserId
              AND NOT EXISTS (
                SELECT 1
                FROM friend_requests fr
                WHERE ((fr.sender_id = :currentUserId AND fr.receiver_id = u.id)
                    OR (fr.sender_id = u.id AND fr.receiver_id = :currentUserId))
                  AND fr.status = 'pending'
              )
              AND NOT EXISTS (
                SELECT 1
                FROM friendships f
                WHERE (f.user1_id = :currentUserId AND f.user2_id = u.id and f.status = 'active')
                   OR (f.user2_id = :currentUserId AND f.user1_id = u.id and f.status = 'active')
              )
            """,
            countQuery = """
                    SELECT COUNT(*)
                                FROM users u
                                WHERE u.id <> :currentUserId
                                  AND NOT EXISTS (
                                      SELECT 1
                                      FROM friend_requests fr
                                      WHERE (fr.sender_id = :currentUserId OR fr.receiver_id = :currentUserId)
                                        AND fr.status = 'pending'
                                  )
                                  AND NOT EXISTS (
                                      SELECT 1
                                      FROM friendships f
                                      WHERE :currentUserId = f.user1_id OR :currentUserId = f.user2_id
                                  )
                    """,
            nativeQuery = true)
    Page<UserJpaEntity> findUsersNotFriendOrPending(UUID currentUserId, Pageable pageable);
}
