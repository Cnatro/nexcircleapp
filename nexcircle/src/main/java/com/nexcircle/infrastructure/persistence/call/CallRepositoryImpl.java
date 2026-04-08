package com.nexcircle.infrastructure.persistence.call;

import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.infrastructure.persistence.call.jpa.CallParticipantJpaRepository;
import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaEntity;
import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaRepository;
import com.nexcircle.infrastructure.persistence.call.mapper.CallPersistenceMapper;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallRepositoryImpl implements CallRepository {
    CallParticipantJpaRepository callParticipantJpaRepository;
    CallSessionJpaRepository callSessionJpaRepository;
    CallPersistenceMapper mapper;

    @Override
    public CallSession saveCallSession(CallSession callSession) {
        var jpaEntity = mapper.toCallSessionJpaEntity(callSession);
        var saved =  callSessionJpaRepository.save(jpaEntity);
        return mapper.toCallSessionDomain(saved);
    }

    @Override
    public void saveParticipant(CallParticipant callParticipant) {
        var jpaEntity = mapper.toCallParticipantJpaEntity(callParticipant);
        callParticipantJpaRepository.save(jpaEntity);
    }

    @Override
    public Optional<CallSession> findCallSessionById(UUID id) {
        return callSessionJpaRepository.findById(id).map(mapper::toCallSessionDomain);
    }

    @Override
    public boolean isParticipant(UUID sessionId, UUID userId) {
        return callParticipantJpaRepository.existsByCallSessionIdAndUserId(sessionId, userId);
    }

    @Override
    public void updateParticipantStatus(UUID sessionId, UUID userId, LocalDateTime joinedAt, LocalDateTime leftAt) {
        var participant = callParticipantJpaRepository.findByCallSessionIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new AppException(MessageCode.CALL_PARTICIPANT_NOT_FOUND));

        if (joinedAt != null) participant.setJoinedAt(joinedAt);
        if (leftAt != null) participant.setLeftAt(leftAt);

        callParticipantJpaRepository.save(participant);
    }

    public CallSession findAndVerifyParticipant(UUID sessionId, UUID userId) {
        CallSession session = findCallSessionById(sessionId)
                .orElseThrow(() -> new AppException(MessageCode.CALL_NOT_FOUND));

        if (!isParticipant(sessionId, userId)) {
            throw new AppException(MessageCode.UNAUTHORIZED, "You are not allowed to action this call");
        }
        return session;
    }

    @Override
    public UUID findOtherParticipant(UUID sessionId, UUID currentUserId) {
        return callParticipantJpaRepository.findByCallSessionId(sessionId)
                .stream()
                .map(p -> p.getUser().getId())
                .filter(id -> !id.equals(currentUserId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No other participant found"));
    }
}
