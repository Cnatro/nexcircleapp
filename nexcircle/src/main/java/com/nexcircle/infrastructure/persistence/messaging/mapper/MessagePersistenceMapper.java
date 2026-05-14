package com.nexcircle.infrastructure.persistence.messaging.mapper;

import com.nexcircle.domain.messaging.entity.Message;
import com.nexcircle.infrastructure.persistence.messaging.jpa.MessageJpaEntity;
import com.nexcircle.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        UserPersistenceMapper.class
})
public interface MessagePersistenceMapper {
    Message toMessageDomain(MessageJpaEntity jpa);
    MessageJpaEntity toMessageJpaEntity(Message domain);
}
