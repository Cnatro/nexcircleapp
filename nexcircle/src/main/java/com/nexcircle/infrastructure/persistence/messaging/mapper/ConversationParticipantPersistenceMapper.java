package com.nexcircle.infrastructure.persistence.messaging.mapper;

import com.nexcircle.domain.messaging.entity.ConversationParticipant;
import com.nexcircle.infrastructure.persistence.messaging.jpa.ConversationParticipantJpaEntity;
import com.nexcircle.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
        UserPersistenceMapper.class, ConversationPersistenceMapper.class
})
public interface ConversationParticipantPersistenceMapper {
    ConversationParticipant toConversationParticipantEntity(ConversationParticipantJpaEntity jpa);
    ConversationParticipantJpaEntity toConversationParticipantJpaEntity(ConversationParticipant conversation);
}
