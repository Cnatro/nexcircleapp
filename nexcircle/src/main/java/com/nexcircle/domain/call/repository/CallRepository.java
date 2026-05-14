package com.nexcircle.domain.call.repository;

import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CallRepository {
    CallSession saveCallSession(CallSession callSession);
    void saveParticipant(CallParticipant callParticipant);
    Optional<CallSession> findCallSessionById(UUID id);
    boolean isParticipant(UUID sessionId, UUID userId);
    void updateParticipantStatus(UUID sessionId, UUID userId, LocalDateTime joinedAt, LocalDateTime leftAt);
    CallSession findAndVerifyParticipant(UUID sessionId, UUID userId);
    UUID findOtherParticipant(UUID sessionId, UUID currentUserId);
}
