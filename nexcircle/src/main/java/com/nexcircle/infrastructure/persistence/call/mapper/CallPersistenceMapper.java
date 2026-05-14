package com.nexcircle.infrastructure.persistence.call.mapper;

import com.nexcircle.domain.call.entity.CallParticipant;
import com.nexcircle.domain.call.entity.CallSession;
import com.nexcircle.infrastructure.persistence.call.jpa.CallParticipantJpaEntity;
import com.nexcircle.infrastructure.persistence.call.jpa.CallSessionJpaEntity;
import com.nexcircle.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UserPersistenceMapper.class
})
public interface CallPersistenceMapper {
    CallSession toCallSessionDomain(CallSessionJpaEntity jpa);
    CallSessionJpaEntity toCallSessionJpaEntity(CallSession domain);

    CallParticipant toCallParticipantDomain(CallParticipantJpaEntity jpa);
    @Mapping(target = "callSession", ignore = true)
    @Mapping(target = "user", ignore = true)
    CallParticipantJpaEntity toCallParticipantJpaEntity(CallParticipant domain);

}
