package com.nexcircle.infrastructure.persistence.call;

import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.domain.call.repository.CallRepository;
import com.nexcircle.infrastructure.persistence.call.jpa.CallParticipantJpaRepository;
import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaEntity;
import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaRepository;
import com.nexcircle.infrastructure.persistence.call.mapper.CallPersistenceMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

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
}
