package com.nexcircle.domain.call.repository;

import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;

import java.util.Optional;
import java.util.UUID;

public interface CallRepository {
    CallSession saveCallSession(CallSession callSession);
    void saveParticipant(CallParticipant callParticipant);
    Optional<CallSession> findCallSessionById(UUID id);
}
