package com.nexcircle.infrastructure.persistence.user.jpa;

import com.nexcircle.domain.user.entity.User;
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
}
