package com.nexcircle.infrastructure.persistence.call.jpa;

import com.nexcircle.domain.call.entity.CallParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CallParticipantJpaRepository extends JpaRepository<CallParticipantJpaEntity, UUID> {
    Optional<CallParticipantJpaEntity> findByCallSessionIdAndUserId(UUID sessionId, UUID userId);
    boolean existByCallSessionIdAndUserId(UUID sessionId, UUID userId);
}
